package shu.silence;

import shu.silence.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbService {
    public void addUser(Users user) {
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = null;
        String sql = "insert into userinfo(username, password) values(?,?)";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Users hasUser(String username, String password) {
        Users user = new Users();
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from userinfo where (username = ? and password = ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setUserid(rs.getInt("user_id"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(rs, ps, connection);
        }

        return user;
    }

    public boolean hasSameName(String username) {
        String name = null;
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from userinfo where username = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("username");
                return name.equals(username);
            } else {
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(rs, ps, connection);
        }
        return false;
    }

}