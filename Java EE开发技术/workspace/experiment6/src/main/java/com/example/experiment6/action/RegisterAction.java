package com.example.experiment6.action;

import com.example.experiment6.db.DB;
import com.example.experiment6.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class RegisterAction extends ActionSupport {
    private User user = null;

    public String execute() {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();

        DB DBConn = new DB();
        String sql = "insert into TEST..userTable (username,password) values(?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            if (user != null) {
                pstmt.executeUpdate();
            }
            pstmt.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }

    }
}