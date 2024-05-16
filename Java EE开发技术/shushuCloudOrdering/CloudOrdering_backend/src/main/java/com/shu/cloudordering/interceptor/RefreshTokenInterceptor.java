package com.shu.cloudordering.interceptor;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.shu.cloudordering.model.dto.UserDTO;
import com.shu.cloudordering.utils.IpUtil;
import com.shu.cloudordering.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.shu.cloudordering.constants.RedisConstants.LOGIN_USER_KEY;
import static com.shu.cloudordering.constants.RedisConstants.LOGIN_USER_TTL;

/**
 * 只要用户登录后进行了操作（一切操作），就必须持续刷新token过期时间，提高用户使用体验
 * 而不是只有访问被拦截的几个接口时才刷新过期时间
 * 该拦截器在登录拦截器之前执行
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = IpUtil.getIpAddr(request);
        // 1.获取请求头中的token
        String token = request.getHeader("TOKEN");
        log.info("TOKEN: " + token + "的访问者IP地址：" + ipAddress);
        if (StrUtil.isBlank(token)) {
            log.debug("TOKEN为空~");
            return true;
        }
        // 2.基于TOKEN获取redis中的用户
        String key = LOGIN_USER_KEY + token;
        String userDtoStr = stringRedisTemplate.opsForValue().get(key);
        // 3.判断用户是否存在
        if (StrUtil.isBlank(userDtoStr)) {
            log.debug("用户不存在~");
            return true;
        }
        // 5.将查询到的hash数据转为UserDTO
        UserDTO userDTO = JSONUtil.toBean(userDtoStr, UserDTO.class);
        // 6.存在，保存用户信息到 ThreadLocal
        UserHolder.saveUser(userDTO);
        // 7.刷新token有效期
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.DAYS);
        log.debug("刷新TOKEN~");
        // 8.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户，防止内存泄漏
        UserHolder.removeUser();
    }
}
