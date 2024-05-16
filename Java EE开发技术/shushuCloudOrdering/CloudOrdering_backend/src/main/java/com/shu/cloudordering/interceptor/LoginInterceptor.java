package com.shu.cloudordering.interceptor;


import com.shu.cloudordering.model.dto.UserDTO;
import com.shu.cloudordering.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 判断用户是否登录
        UserDTO userDTO = UserHolder.getUser();
        if (userDTO == null) {
            // 不放行
            log.error("当前用户未登录");
            return false;
        }
        // 2. 放行
        return true;
    }
}
