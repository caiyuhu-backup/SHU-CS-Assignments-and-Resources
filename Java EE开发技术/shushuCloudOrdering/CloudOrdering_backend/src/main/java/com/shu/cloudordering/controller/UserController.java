package com.shu.cloudordering.controller;

import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.dto.LoginFormDTO;
import com.shu.cloudordering.model.entity.Users;
import com.shu.cloudordering.service.UsersService;
import com.shu.cloudordering.utils.UserHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itbear-shu-20121706
 * @date 2022/3/27 22:17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UsersService usersService;

    /**
     * 发送手机验证码
     */
    @PostMapping("code")
    public Result sendCode(@RequestParam("phone") String phone) {
        // 发送短信验证码并保存验证码
        return usersService.sendCode(phone);
    }

    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、验证码；或者手机号、密码
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginFormDTO loginForm){
        // 实现登录功能
        return usersService.login(loginForm);
    }

    @PostMapping("info")
    public Result getUserInfo(){
        return Result.success("获取成功~", UserHolder.getUser());
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader("TOKEN") String token){
        return usersService.logout(token);
    }

    @GetMapping("list")
    public Result list() {
        return Result.success("获取成功~", usersService.list());
    }
}
