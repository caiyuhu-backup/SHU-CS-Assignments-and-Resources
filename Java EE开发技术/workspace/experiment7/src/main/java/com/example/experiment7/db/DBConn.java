package com.example.experiment7.db;



import com.example.experiment7.model.User;

import java.sql.*;

public class DBConn {
    public Connection ct;
    public PreparedStatement pstmt;

    public DBConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "hcy123450");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User checkUser(String username, String password) {
        try {
            pstmt = ct.prepareStatement("select * from usertable where username=? and password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public String getUserName(int id) {
        String username = null;
        try {
            pstmt = ct.prepareStatement("select username from userTable where id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                username = rs.getString(1);
            }
            return username;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean saveUser(String username, String pwd) {
        try {
            pstmt = ct.prepareStatement("insert into userTable (username, password) values(?,?)");
            pstmt.setString(1, username);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
