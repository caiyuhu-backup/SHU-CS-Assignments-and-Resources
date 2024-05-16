package src.crawler;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Liu Yuan
 * @create 2021-10-23-15:12
 * @describe JDBC工具类
 */
public class Utils
{
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    //获取连接、用户、密码、驱动
    static
    {
        try
        {
            Properties pro = new Properties();
            try
            {
                //pro.load(new FileReader("E:\\刘元\\学校\\大学\\大二\\冬季学期\\JavaSE\\项目\\src\\main\\java\\src\\jdbc.properties"));
                pro.load(new FileReader("E:\\刘元\\GitHub Repositories\\MyProjects\\2. Java\\1. Crawlers\\2. Advanced Edition\\src\\main\\java\\src\\jdbc1.properties"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            Class.forName(driver);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        /**
         * @function getConnection
         * @param []
         * @return java.sql.Connection
         * @description 获取数据库连接
         * @author Liu Yuan
         * @date 2021/10/23
         */
        return DriverManager.getConnection(url,user,password);
    }

    public static void close(Statement statement, Connection connection)
    {
        /**
         * @function close
         * @param [statement, connection]
         * @return void
         * @description 关闭连接
         * @author Liu Yuan
         * @date 2021/10/23
         */
        close(null,statement,connection);
    }

    public static void close(ResultSet resultSet,Statement statement, Connection connection)
    {
        /**
         * @function close
         * @param [resultSet, statement, connection]
         * @return void
         * @description 关闭连接
         * @author Liu Yuan
         * @date 2021/10/23
         */
        if(resultSet != null)
        {
            try
            {
                resultSet.close();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
        if(statement != null)
        {
            try
            {
                statement.close();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
        if(connection != null)
        {
            try
            {
                connection.close();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
    }
}
