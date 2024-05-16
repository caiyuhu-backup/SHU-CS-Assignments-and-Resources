package com.example.experiment8.controller;

import com.example.experiment8.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class LoginController {
    // 处理登录的控制器
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView checkLogin(User user) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        ModelAndView mav = new ModelAndView();

        int rows = session.selectOne("mapper.userMapper.findUser", user);
        if (rows > 0) {
            mav.addObject("users", user);
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