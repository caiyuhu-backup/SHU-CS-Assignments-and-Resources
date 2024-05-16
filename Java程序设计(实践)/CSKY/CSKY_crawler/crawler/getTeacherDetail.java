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
 * @create 2022-01-10-11:16
 * @describe 获取教师的详细信息
 */
public class getTeacherDetail
{
    static Connection connection = null;                //连接
    static PreparedStatement statement_query = null;    //查询statement
    static PreparedStatement statement_update = null;   //更新statement
    static Document document = null;                    //网页文件

    public static void writeDetail()
    {
        //查询现有数据sql
        String sql_query = "select * from teachers where tname not like \"%汇总%\"";
        //添加数据sql
        String sql_update = "update teachers set tphonenumber=? ,tfax=? ,temail=? ,studydir=? ,eduresume=? ,proresume=? where tURL=?";

        String teacher_URL = null;              //教师网页URL
        String teacher_phone = null;            //教师电话
        String teacher_fax = null;              //教师传真
        String teacher_email = null;            //教师邮箱
        String teacher_studyDirection = null;   //教师研究方向
        String teacher_educationResume = null;  //教师教育履历
        String teacher_proResume = null;        //教师专业履历
        String[] stringSplit = null;            //字符串分割

        int count = 0;                          //记录写入条数

        try
        {
            connection = Utils.getConnection();
            statement_query = connection.prepareStatement(sql_query);
            statement_update = connection.prepareStatement(sql_update);
            ResultSet resultSet = statement_query.executeQuery();
            while (resultSet.next())
            {
                //每次查询开始之前初始化，防止将之前的数据写到数据库中
                teacher_URL = null;
                teacher_phone = null;
                teacher_fax = null;
                teacher_email = null;
                teacher_studyDirection = null;
                teacher_educationResume = null;
                teacher_proResume = null;
                stringSplit = null;

                teacher_URL = resultSet.getString(4);                   //从数据库中获取教师URL
//                System.out.println(teacher_URL);
                document = Jsoup.parse(new URL(teacher_URL), 10000);    //根据URL访问教师主页
                Elements elements = document.getElementsByTag("p");         //根据标签爬取
//                System.out.println(elements);

                //开始遍历元素集
                for (Element teacherInfo : elements)
                {
                    //爬取邮箱
                    if (teacherInfo.text().contains("mail"))
                    {
//                        System.out.println(teacherInfo);
//                        System.out.println(teacherInfo.text());
                        if (teacherInfo.text().contains(": "))
                        {
                            stringSplit = teacherInfo.text().split(": ");
                        } else if (teacherInfo.text().contains(":"))
                        {
                            stringSplit = teacherInfo.text().split(":");
                        } else if (teacherInfo.text().contains("："))
                        {
                            stringSplit = teacherInfo.text().split("：");
                        } else
                        {
                            stringSplit = teacherInfo.text().split("");
                        }
                        for (String slice : stringSplit)
                        {
                            if ((slice.contains(".cn") ||slice.contains(".net")) &&
                                    (slice.contains("at") || slice.contains("@")))
                            {
                                teacher_email = slice;
                            }
                        }
                    }
                    //爬取研究方向
                    if(teacherInfo.text().contains("研究方向"))
                    {
                        teacher_studyDirection = teacherInfo.text();
                    }
                    //爬取教育经历
                    if(teacherInfo.text().contains("教育经历"))
                    {
                        teacher_educationResume = teacherInfo.text();
                    }
                    //爬取传真
                    if(teacherInfo.text().contains("传真"))
                    {
                        teacher_fax = teacherInfo.text();
                    }
                    //爬取专业
                    if(teacherInfo.text().contains("专业"))
                    {
                        teacher_proResume = teacherInfo.text();
                    }
                    //爬取电话
                    if(teacherInfo.text().contains("电话"))
                    {
                        teacher_phone = teacherInfo.text();
                    }
                }
                //输出测试
                /*System.out.println("-----完成一名教师----");
                System.out.println("URL：" + teacher_URL);
                System.out.println("电话： " + teacher_phone);
                System.out.println("传真： " + teacher_fax);
                System.out.println("邮箱： " + teacher_email);
                System.out.println("方向： " + teacher_studyDirection);
                System.out.println("教育： " + teacher_educationResume);
                System.out.println("专业： " + teacher_proResume);
                System.out.println("--------------------\n");*/

                //写入数据
                statement_update.setString(1,teacher_phone);
                statement_update.setString(2,teacher_fax);
                statement_update.setString(3,teacher_email);
                statement_update.setString(4,teacher_studyDirection);
                statement_update.setString(5,teacher_educationResume);
                statement_update.setString(6,teacher_proResume);
                statement_update.setString(7,teacher_URL);
                statement_update.executeUpdate();
                System.out.println("完成第 " + (++count) + " 条数据写入");
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
