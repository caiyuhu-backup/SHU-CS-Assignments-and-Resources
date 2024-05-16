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
 * @create 2022-01-10-16:20
 * @describe 将数据库中schoolID中的信息转存到school中，然后爬取详细的信息
 */
public class SchoolOperating
{
    static Connection connection = null;
    static PreparedStatement statement_query = null;
    static PreparedStatement statement_update = null;
    static String sql_query = null;
    static String sql_update = null;

    public static void transport()
    {
        /**
         * @function transport
         * @param []
         * @return void
         * @description 转移信息，将schoolid（临时表）中的学校id和名称转移到school表中
         * @author Liu Yuan
         * @time 16:35
         */
        sql_query = "select * from schoolid";
        sql_update = "insert into school values(null,?,null,null,?,null)";

        String school_name = null;      //学校名称
        String school_tid = null;       //学校的tid

        int count = 0;                  //写入数据计数

        try
        {
            connection = Utils.getConnection();
            statement_query = connection.prepareStatement(sql_query);
            statement_update = connection.prepareStatement(sql_update);
            ResultSet resultSet = statement_query.executeQuery();

            while (resultSet.next())
            {
                school_tid = resultSet.getString(1);
                school_name = resultSet.getString(2);

                statement_update.setString(1, school_name);
                statement_update.setInt(2, new Integer(school_tid).intValue());
                statement_update.executeUpdate();

                System.out.println("-----第 " + (++count) + " 条数据写入完毕-----");
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public static void getRecruitment()
    {
        /**
         * @function getRecruitment
         * @param []
         * @return void
         * @description 爬取学校的招生简章，如果是文字，则全部保存，如果是pdf，则保存URL，并以 "URL: " 开头
         * @author Liu Yuan
         * @time 16:36
         */

        //所有学校的网页URL开头相同，schoolPageURL + schoolPageID + .shtml 就是对应学校网页，必须通过学校网页访问招生简章网页
        String schoolPageURL = "http://www.chinakaoyan.com/graduate/grad/schoolID/";

        sql_query = "SELECT * FROM school";
        sql_update = "UPDATE school SET recruitment = ? WHERE tid = ?";

        int school_tid = 0;
        String school_name = null;
        String school_recruitmentURL = null;
        String school_file = null;

        int count = 0;

        try
        {
            connection = Utils.getConnection();
            statement_query = connection.prepareStatement(sql_query);
            ResultSet resultSet = statement_query.executeQuery();

            Document document = null;
            Elements elements = null;

            while (resultSet.next())
            {
                school_tid = resultSet.getInt(5);
                school_name = resultSet.getString(2);
                System.out.println(school_name + " - " + school_tid);
                document = Jsoup.parse(new URL(schoolPageURL + school_tid + ".shtml"), 30000);
                elements = document.getElementsByAttributeValueContaining("target", "_blank");
                for (Element element : elements)
                {
                    if (element.text().contains("考研招生简章"))
                    {
                        school_recruitmentURL = "http://www.chinakaoyan.com" + element.toString().split("\"")[3];
                        Document document_recruitment = Jsoup.parse(new URL(school_recruitmentURL), 30000);
                        if(document_recruitment.toString().contains("点击查看"))
                        {
                            Elements getElements = document_recruitment.getElementsByAttributeValueContaining("target","_blank");
                            for(Element html : getElements)
                            {
                                if(html.text().contains("点击查看") || html.text().contains(".pdf"))
                                {
                                    school_file = "URL: " + html.toString().split("\"")[3];
//                                    System.out.println(school_file);
                                    break;
                                }
                            }
                        }
                        else
                        {
                            Elements getElements = document_recruitment.getElementsByClass("arc-body font14");
                            for(Element html : getElements)
                            {
                                if(html.text().contains("招生简章") || html.text().contains("招生章程") || html.text().contains("报考条件"))
                                {
                                    school_file = html.text();
                                    break;
                                }
                            }
                        }

                        statement_update = connection.prepareStatement(sql_update);
                        statement_update.setString(1, school_file);
                        statement_update.setInt(2, school_tid);
//                        System.out.println(statement_update);
                        statement_update.executeUpdate();

                        System.out.println("第 " + (++count) + " 条写入完毕\n");
                        break;
                    }
                }

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
