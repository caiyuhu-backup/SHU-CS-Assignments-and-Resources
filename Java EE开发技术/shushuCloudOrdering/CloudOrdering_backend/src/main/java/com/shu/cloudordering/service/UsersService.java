package com.shu.cloudordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.dto.LoginFormDTO;
import com.shu.cloudordering.model.entity.Users;

/**
* @author Administrator
* @description 针对表【users(用户 )】的数据库操作Service
* @createDate 2022-03-27 22:16:11
*/
public interface UsersService extends IService<Users> {

    Result sendCode(String phone);

    Result login(LoginFormDTO loginForm);

    Result logout(String token);
}
