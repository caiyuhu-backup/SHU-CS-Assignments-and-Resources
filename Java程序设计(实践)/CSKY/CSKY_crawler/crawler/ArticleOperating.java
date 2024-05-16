package src.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Liu Yuan
 * @create 2022-01-10-19:17
 * @describe 将文章的相关信息存入数据库
 */
public class ArticleOperating
{
    static Connection connection;
    static String sql_query;
    static String sql_update;
    static PreparedStatement statement_query;
    static PreparedStatement statement_update;

    static
    {
        try
        {
            connection = Utils.getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static ResultSet getSchools() throws SQLException
    {
        /**
         * @function getSchools
         * @param []
         * @return java.sql.ResultSet
         * @description 从数据库查询学校名称和id
         * @author Liu Yuan
         * @time 19:59
         */
        String sql;
        sql = "SELECT * FROM schoolid";
        try
        {
            statement_query = connection.prepareStatement(sql);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return statement_query.executeQuery();
    }

    public static String generateURL(int id)
    {
        /**
         * @function generateURL
         * @param [id]
         * @return java.lang.String
         * @description 组合生成文件所在URL
         * @author Liu Yuan
         * @time 20:00
         */
        return "http://www.chinakaoyan.com/graduate/InfoList/class/kcap/schoolID/" + id + ".shtml";
    }

    public static void getArticles()
    {
        /**
         * @function getArticles
         * @param []
         * @return void
         * @description 将文档信息保存到数据库中
         * @author Liu Yuan
         * @time 20:00
         */
        sql_update = "insert into articles values(null,?,?,?)";

        String schoolName = null;           //学校名称
        String schoolID = null;             //学校id
        String schoolArticleURL = null;     //学校的文章所在网页

        String articleName = null;          //文章名称
        String articleURL = null;           //文章URL

        int count = 0;                      //计数器

        try
        {
            statement_update = connection.prepareStatement(sql_update);

            ResultSet resultSet = getSchools();
            while (resultSet.next())
            {
                schoolName = resultSet.getString(2);
                schoolArticleURL = generateURL(new Integer(schoolID = resultSet.getString(1)).intValue());
                System.out.println(schoolName + " - " + schoolID + " - " + schoolArticleURL);
                Document document = Jsoup.parse(new URL(schoolArticleURL), 30000);
                /*Elements elements = document.getElementsByClass("list-group font14");
                for (Element element : elements)
                {
                    System.out.println(element);
                }*/

                Elements elements = document.getElementsByAttributeValueContaining("href", "/graduate/info/nid/");
                for (Element element : elements)
                {
                    //通过条件筛选获得目标内容
                    if (element.text().contains(schoolName))
                    {
//                        System.out.println(element);
                        //字符串分割，获得文章URL
                        articleURL = "http://www.chinakaoyan.com" + element.toString().split("\"")[1];
                        //文章名称
                        articleName = element.text();
                        //sout
                        /*System.out.println(articleURL);
                        System.out.println(articleName);*/

                        statement_update.setInt(1,new Integer(schoolID).intValue());
                        statement_update.setString(2,articleName);
                        statement_update.setString(3,"URL: " + articleURL);
//                        System.out.println(statement_update);
                        statement_update.executeUpdate();

                        System.out.println("第 " + (++count) + " 条插入完成");
                    }
                }
                System.out.println(schoolName + "插入完毕, 共插入 " + count + "条\n");
                count = 0;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
