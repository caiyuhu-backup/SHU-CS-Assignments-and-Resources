package com.example.experiment7.controller;

import com.example.experiment7.db.DBConn;
import com.example.experiment7.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    // 处理登录的控制器
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView checkLogin(User user) {
        ModelAndView mav = new ModelAndView();
        DBConn dbConn = new DBConn();
        User u = dbConn.checkUser(user.getUsername(), user.getPassword());
        if (u != null) {
            mav.addObject("users", u);
            // 跳转的页面
            mav.setViewName("success.jsp");
        } else {
            mav.addObject("msg", "用户名或者密码错误");
            // 跳转的页面
            mav.setViewName("login.jsp");
        }
        return mav;
    }

}