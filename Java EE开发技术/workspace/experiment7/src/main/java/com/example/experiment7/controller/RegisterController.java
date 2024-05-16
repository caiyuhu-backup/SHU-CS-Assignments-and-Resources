package com.example.experiment7.controller;

import com.example.experiment7.db.DBConn;
import com.example.experiment7.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    // 处理登录的控制器
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(User user) {
        ModelAndView mav = new ModelAndView();
        DBConn dbConn = new DBConn();
        boolean b = dbConn.saveUser(user.getUsername(), user.getPassword());
        if (b) {
            mav.addObject("users", user);
            // 跳转的页面
            mav.setViewName("login.jsp");
        } else {
            mav.addObject("msg", "用户名或者密码错误");
            // 跳转的页面
            mav.setViewName("register.jsp");
        }
        return mav;
    }
}
