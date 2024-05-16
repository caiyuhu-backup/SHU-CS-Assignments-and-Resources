package src.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Liu Yuan
 * @create 2022-01-08-13:34
 * @describe 爬取学校的网页ID，存到数据库中
 */
public class getSchoolID
{
    static Connection connection = null;
    static PreparedStatement statement = null;
    static String sql = null;

    static final String URL = "http://www.chinakaoyan.com/graduate/zhx.shtml";

    public static void readID() throws IOException
    {
        /**
         * @function readID
         * @param []
         * @return void
         * @description 获取学校网页id
         * @author Liu Yuan
         * @time 14:26
         */

        String html = null;
        String schoolURL = null;
        String schoolID = null;
        String schoolName = null;

        //连接网页
        Document document = Jsoup.parse(new URL(URL), 10000);
        //根据属性值匹配抓取元素
        Elements schoolList = document.getElementsByAttributeValueContaining("href", "/graduate/grad/schoolID/");
        //抓取结果
        /*
            <a href="/graduate/grad/schoolID/82.shtml" title="北京大学研究生院" target="_blank">北京大学</a>
            <a href="/graduate/grad/schoolID/83.shtml" title="中国人民大学研究生院" target="_blank">中国人民大学</a>
         */

        for (Element school : schoolList)
        {
            html = school.toString();                                                                 //将元素转换为字符串，方便下一步操作
            /*
            <a href="/graduate/grad/schoolID/82.shtml" title="北京大学研究生院" target="_blank">北京大学</a>
            */
            schoolURL = html.split("\"")[1];                                                    //字符串分割，获取URL部分
            /*
            /graduate/grad/schoolID/82.shtml
             */

            schoolID = schoolURL.split("/")[4].split(".shtml")[0];                        //获取学校网页ID
            schoolName = html.split("\"")[6].split(">")[1].split("<")[0];           //获取学校名称

            sql = "insert into schoolid values(?,?)";                                                   //准备sql
            try
            {
                connection = Utils.getConnection();                                                     //获取连接
                statement = connection.prepareStatement(sql);                                           //准备statement

                //设置statement
                statement.setString(1,schoolID);
                statement.setString(2,schoolName);

                statement.executeUpdate();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

            System.out.println(schoolName + "写入成功");
        }
    }
}
