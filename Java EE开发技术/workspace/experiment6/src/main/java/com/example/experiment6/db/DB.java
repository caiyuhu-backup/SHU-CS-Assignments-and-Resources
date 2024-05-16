package com.example.experiment6.db;

import com.example.experiment6.model.LyTable;
import com.example.experiment6.model.User;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    public Connection ct;
    public PreparedStatement pstmt;

    public DB() {
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

    public ArrayList findLyInfo() {
        try {
            pstmt = ct.prepareStatement("select * from lyTable");
            ArrayList al = new ArrayList();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LyTable ly = new LyTable();
                ly.setId(rs.getInt(1));
                ly.setUserId(rs.getInt(2));
                ly.setDate(rs.getDate(3));
                ly.setTitle(rs.getString(4));
                ly.setContent(rs.getString(5));
                al.add(ly);
            }
            return al;
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

    public boolean addInfo(LyTable ly) {
        try {
            pstmt = ct.prepareStatement("insert into lyTable (userId, date, title, content) values(?,?,?,?)");
            pstmt.setInt(1, ly.getUserId());
            pstmt.setDate(2, (Date) ly.getDate());
            pstmt.setString(3, ly.getTitle());
            pstmt.setString(4, ly.getContent());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(String username, String pwd) {
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
