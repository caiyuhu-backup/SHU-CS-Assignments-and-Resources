package com.example.experiment6.action;

import com.example.experiment6.db.DB;
import com.example.experiment6.model.LyTable;
import com.example.experiment6.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport {
    private User user;

    public String execute() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();


        String username = user.getUsername();
        String password = user.getPassword();
        boolean validated = false;
        User validatedUser = null;


        DB DBConn = new DB();
        String sql = "select * from TEST..userTable";
        ResultSet rs = DBConn.e(sql);
        while (rs.next()) {
            if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                validatedUser = new User();
                validatedUser.setId(rs.getInt(1));
                validatedUser.setUsername(username);
                validatedUser.setPassword(password);
                session.put("user", validatedUser);
                validated = true;
            }
        }
        if (validated) {
            List<LyTable> al = new ArrayList();
            ArrayList lyInfo = DBConn.findLyInfo();
            LyTable ly = null;
            while (rs.next()) {
                ly = new LyTable();
                ly.setId(rs.getInt(1));
                ly.setUserId(rs.getInt(2));
                ly.setDate(rs.getDate(3));
                ly.setTitle(rs.getString(4));
                ly.setContent(rs.getString(5));
                al.add(ly);
            }
            rs.close();
            session.put("al", al);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
}