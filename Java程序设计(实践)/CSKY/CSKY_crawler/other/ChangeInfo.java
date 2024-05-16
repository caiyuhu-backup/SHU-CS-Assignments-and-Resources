package src.other;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import src.crawler.Utils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Liu Yuan
 * @create 2022-02-26-14:21
 * @describe 因后期需求变化对已有数据进行修改
 */
public class ChangeInfo {
    static Connection connection;
    static String sql_query1;
    static String sql_query2;
    static String sql_update;
    static PreparedStatement statement_query1;
    static PreparedStatement statement_query2;
    static PreparedStatement statement_update;

    static {
        try {
            connection = Utils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void change_collegeInfo_to_schoolID() throws SQLException {
        /**
         * @function change_collegeInfo_to_schoolID
         * @param []
         * @return void
         * @description 将cs表中的collegename改为对应的学校id
         * @author Liu Yuan
         * @time 12:45
         */
        sql_query1 = "select * from csky.school";
        sql_query2 = "select * from csky.cs";
        sql_update = "update csky.cs set collegename = ? where collegename = ?";
        String school_id = "";
        String query_collegeName = "";
        try {
            statement_query1 = connection.prepareStatement(sql_query1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement_query2 = connection.prepareStatement(sql_query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet_school = statement_query1.executeQuery();
        ResultSet resultSet_cs = statement_query2.executeQuery();

        //遍历cs表结果集
        while (resultSet_cs.next()) {
            query_collegeName = resultSet_cs.getString(3);
            //遍历school结果集
            while (resultSet_school.next())
                if (query_collegeName.equals(resultSet_school.getString(2))) {
                    school_id = String.valueOf(resultSet_school.getInt(1));
                    //System.out.println(school_id);
                    resultSet_school.beforeFirst();         //重置resultset指针
                    break;
                }
            statement_update = connection.prepareStatement(sql_update);
            statement_update.setString(2, query_collegeName);
            statement_update.setString(1, school_id);
            System.out.println(statement_update);
            statement_update.executeUpdate();
        }

    }

    public static void change_URL_to_text() throws SQLException, IOException {
        /**
         * @function change_URL_to_text
         * @param []
         * @return void
         * @description 将存储的URL改为对应网页的HTML语句
         * @author Liu Yuan
         * @time 12:49
         */
        String url = null;
        ResultSet resultSet = null;
        Document document = null;
        Elements elements = null;
        String getHref = null;
        String judge = null;
        sql_query1 = "SELECT * FROM csky.articles";
        sql_update = "update csky.articles set content = ? where content = ?";
        try {
            statement_query1 = connection.prepareStatement(sql_query1);
            resultSet = statement_query1.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (resultSet.next()) {
            url = resultSet.getString(5);               //获取URL

            //如果读到的是已经修改过的信息，跳过
            if (url.startsWith("<div") || !url.startsWith("http")) {
                System.out.println("jump");
                continue;
            }
            System.out.println(url);
            document = Jsoup.parse(new URL(url), 10000);         //获取网页
            elements = document.select("div.arc-body.font14");      //选择“arc-body font14”的div
            for (Element ele : elements) {
                //System.out.println(ele.getElementsByAttributeValueContaining("color", "#008080"));
                //除了目标HTML，其他都包含“style”，故跳过
                if (!ele.toString().contains("style")) {
                    statement_update = connection.prepareStatement(sql_update);
                    statement_update.setString(2, url);
                    statement_update.setString(1, ele.toString());
//                    System.out.println(statement_update);
                    statement_update.executeUpdate();
                    System.out.println("finished");
                }
            }
            System.out.println();
        }
    }
}
