package db;

import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DB {
    Connection ct;
    PreparedStatement pstmt;

    public DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8", "root", "hcy123450");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User checkUser(String username, String password) {
        try {
            pstmt = ct.prepareStatement("select * from user where username=? and password=?");
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
            pstmt = ct.prepareStatement("select * from product");
            ArrayList al = new ArrayList();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product ly = new Product();
                ly.setId(rs.getInt(1));
                ly.setProductCode(rs.getInt(2));
                ly.setProductName(rs.getString(3));
                al.add(ly);
            }
            return al;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList findUserInfo() {
        try {
            pstmt = ct.prepareStatement("select * from user");
            ArrayList al = new ArrayList();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                al.add(user);
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
            pstmt = ct.prepareStatement("select username from user where id=?");
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

    public boolean addInfo(Product product) {
        try {
            pstmt = ct.prepareStatement("insert into product (id, productCode, productName) values(?,?,?)");
            pstmt.setInt(1, product.getId());
            pstmt.setInt(2, product.getProductCode());
            pstmt.setString(3, product.getProductName());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(String username, String pwd) {
        try {
            pstmt = ct.prepareStatement("insert into user (username, password) values(?,?)");
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
