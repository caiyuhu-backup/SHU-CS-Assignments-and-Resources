package com.shu.cloudordering.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.mapper.ExpPhoneMapper;
import com.shu.cloudordering.mapper.UsersMapper;
import com.shu.cloudordering.model.dto.LoginFormDTO;
import com.shu.cloudordering.model.dto.UserDTO;
import com.shu.cloudordering.model.entity.ExpPhone;
import com.shu.cloudordering.model.entity.Users;
import com.shu.cloudordering.model.entity.Vendor;
import com.shu.cloudordering.service.UsersService;
import com.shu.cloudordering.service.VendorService;
import com.shu.cloudordering.utils.RegexUtils;
import com.shu.cloudordering.utils.SMSUtil;
import com.shu.cloudordering.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.*;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.shu.cloudordering.constants.RedisConstants.*;
import static com.shu.cloudordering.constants.UserConstants.USER_NICK_NAME_PREFIX;
import static com.shu.cloudordering.constants.UserConstants.USER_REAL_NAME_PREFIX;

/**
 * @author Administrator
 * @description 针对表【users(用户 )】的数据库操作Service实现
 * @createDate 2022-03-27 22:16:11
 */
@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private VendorService vendorService;
    @Resource
    private ExpPhoneMapper expPhoneMapper;

    @Override
    public Result sendCode(String phone) {
        // 1.校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误~");
        }
        // 判断是否已经发送过验证码
        String isSendCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        if (isSendCode != null) {
            return Result.fail("不能重复发送验证码~");
        }
        // 3.符合，生成验证码
        boolean isExpPhone = false;
        List<ExpPhone> expPhoneList = expPhoneMapper.selectList(null);
        for (int i = 0; i < expPhoneList.size(); i++) {
            if (expPhoneList.get(i).getPhone().equals(phone)) {
                isExpPhone = true;
                break;
            }
        }
        String code = "123456";
        if (!isExpPhone) {
            code = RandomUtil.randomNumbers(6);
        }

        // 4.保存验证码到 redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        // 5.发送验证码
        if (!isExpPhone) {
            SMSUtil.batchSend(code, phone);
        }

        // 返回ok
        return Result.success("验证码发送成功~", null);
    }

    @Override
    public Result login(LoginFormDTO loginForm) {
        // 1.校验手机号和验证码
        String phone = loginForm.getPhone();
        String code = loginForm.getCode();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误！");
        }
        if (RegexUtils.isCodeInvalid(code)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("验证码格式错误！");
        }
        // 3.从redis获取验证码并校验
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        if (cacheCode == null || !cacheCode.equals(code)) {
            // 不一致，报错
            return Result.fail("验证码错误！");
        }
        // 4.一致，根据手机号查询用户 select * from tb_user where phone = ?
        Users user = query().eq("user_mobile", phone).one();
        // 5.判断用户是否存在
        if (user == null) {
            // 6.不存在，创建新用户并保存
            user = createUserWithPhone(phone, loginForm.getIsVendor());
        }
        // 7.保存用户信息到 redis中
        // 7.1.随机生成token，作为登录令牌
        String token = UUID.randomUUID().toString(true);
        // 7.2.将User对象转为HashMap存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        // 7.3.存储：key为token，value为userDTO对象
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForValue().set(tokenKey, JSONUtil.toJsonStr(userDTO), LOGIN_USER_TTL, TimeUnit.DAYS);
        // 8.返回token
        return Result.success("登录成功~", token);
    }

    @Override
    public Result logout(String token) {
        // 删除token
        Boolean delete = stringRedisTemplate.delete(LOGIN_USER_KEY + token);
        stringRedisTemplate.delete(LOGIN_CODE_KEY + UserHolder.getUser().getUserMobile());
        if (Boolean.TRUE.equals(delete)) {
            return Result.success("退出成功~", null);
        }
        return Result.fail("退出失败~");
    }

    /**
     * 根据手机号创建新用户
     *
     * @param phone
     * @return User
     */
    private Users createUserWithPhone(String phone, Integer isVendor) {
        // 1.创建用户
        Users user = new Users();
        user.setUserMobile(phone);
        user.setUsername(RandomUtil.randomString(9));
        user.setUserImg("http://cdn.itbear666.top/avatar/" + RandomUtil.randomInt(6, 16) + ".png");
        user.setNickname(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        user.setRealname(USER_REAL_NAME_PREFIX + RandomUtil.randomNumbers(5) + "号");
        user.setUserRegtime(new Date());
        user.setUserModtime(new Date());
        // 给该用户创建商户
        if (isVendor != null && isVendor == 1) {
            Vendor vendor = new Vendor();
            vendor.setScore(0.0);
            vendor.setAveragePrice(0.0);
            vendorService.save(vendor);
            user.setVendorId(vendor.getVendorId());
        }
        // 2.保存用户
        save(user);
        return user;
    }
}
