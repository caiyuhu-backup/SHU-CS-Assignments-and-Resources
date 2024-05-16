package src.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import src.classes.Teacher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Liu Yuan
 * @create 2022-01-08-15:40
 * @describe 获取所有教师的基本信息
 */
public class getTeacherInfo
{
    //所有学校的导师信息网页URL开头相同，schoolTeacherPageURL + schoolPageID + .shtml 就是对应导师信息网页
    private static final String schoolTeacherPageURL = "http://www.chinakaoyan.com/graduate/InfoList/class/ds/schoolID/";

    static Connection connection = null;
    static PreparedStatement statement = null;

    public static int getTeacherPageCount(String url) throws IOException
    {
        /**
         * @function getTeacherPageCount
         * @param [url]
         * @return int
         * @description 获取导师页页数
         * @author Liu Yuan
         * @time 16:28
         */
        int pageCount = 0;          //记录导师页页数

        Document document = Jsoup.parse(new URL(url), 10000);

        //获取导师页数
        Elements pageTag = document.getElementsByAttributeValueContaining("title", "最后页");
        //如果抓取到了对应的文本，进行字符串分割，获取页数
        if (pageTag.hasText())
        {
            //虽然Elements中只有一个elem，但是进行遍历
            for (Element tag : pageTag)
            {
//                    System.out.println(tag);
                //保护措施
                if (tag.toString().length() < 57)
                {
                    pageCount = 1;
                }
                //字符串分割，转换为整数
                else
                {
                    pageCount = new Integer(tag.toString().split("/")[8].split(".shtml")[0]);
                }
            }
        }
        //否则页数为1
        else
        {
            pageCount = 1;
        }
//        System.out.println(pageCount);

        return pageCount;
    }

    public static ArrayList<Teacher> getTeacher()
    {
        /**
         * @function getTeacher
         * @param []
         * @return void
         * @description 从数据库中读取大学的ID和名称，访问导师信息页，将导师的信息整理出来，保存在列表中
         * @author Liu Yuan
         * @time 15:45
         */
        ArrayList<Teacher> teachers = new ArrayList<>();

        Teacher teacher = null;                             //Teacher对象指针

        String sql = "select * from schoolid";              //sql语句，查询所有的学校ID，作为教师tid

        int teacherID;                                      //教师tid
        String schoolName = null;                           //学校名称
        String teachersURL = null;                          //教师个人主页URL
        String teacherName = null;                          //教师姓名

        int pageCount = 0;                                  //各学校的导师网页数量
        String pageURL = null;                              //导师网页URL，用于组合

        try
        {
            connection = Utils.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();                             //执行sql，保存结果集

            while (resultSet.next())
            {
                schoolName = resultSet.getString(2);
                System.out.println("开始爬取" + schoolName + "...");                      //打印学校名称

                teacherID = resultSet.getInt(1);                             //将tid赋值为学校id
                teachersURL = schoolTeacherPageURL + teacherID + ".shtml";              //组合生成教师主页URL

                pageCount = getTeacherPageCount(teachersURL);                           //获取页数

                for (int i = 1; i <= pageCount; i++)
                {
                    System.out.println("抓取第 " + i + " 页，共 " + pageCount + " 页");

                    //合成导师整合页URL
                    pageURL = "http://www.chinakaoyan.com/graduate/InfoList/class/ds/schoolID/" + teacherID + "/pagenum/" + i + ".shtml";
                    Document page = Jsoup.parse(new URL(pageURL), 10000);
                    Elements teacherElements = page.getElementsByAttributeValueContaining("href", "/graduate/info/nid/");
//                    System.out.println(teacherElements);

                    //抓取所有教师信息
                    for (Element teacherEle : teacherElements)
                    {
                        String html = teacherEle.toString();            //将Element转换为String，方便进行字符串分割

                        //选取可能的学院名称
                        if (html.contains("信息工程") || html.contains("电子") || html.contains("计算机") && !(html.contains("机械") || html.contains("商务")))
                        {
//                            System.out.println(teacherEle);
//                            System.out.println(html);
                            teachersURL = html.split("\"")[1];                              //字符串分割，获取教师详情页URL
//                            System.out.println(teachersURL);

                            //分情况进行字符串分割，获取教师名称
                            if (html.contains("："))
                            {
                                teacherName = html.split("：")[1].split("<")[0];
                            } else
                            {
                                teacherName = teacherEle.text();
                            }
//                            System.out.println(teacherName);
//                            System.out.println(teachersURL);

                            //生成新对象，加入列表
                            teacher = new Teacher(teacherID, teacherName, "http://www.chinakaoyan.com" + teachersURL);
                            teachers.add(teacher);


                        }
                    }

                    System.out.println("---------抓取结束-----------");
                }

                System.out.println(schoolName + "爬取完毕\n");
                System.out.println("-----------------------------------------");
                System.out.println();
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

//        System.out.println(teachers);
        return teachers;
    }

    public static void insertTeacher_Basic()
    {
        /**
         * @function insertTeacher
         * @param []
         * @return void
         * @description 将收集到的教师信息放入数据库
         * @author Liu Yuan
         * @time 17:25
         */
        ArrayList<Teacher> teachers = getTeacher();
        System.out.println("所有教师信息抓取完毕，开始加入数据库...\n");
        System.out.println("-------------------------------------------------------------");

        String sql = "insert into teachers values(null,?,?,?,null,null,null,null,null,null)";
        Connection connection = null;
        PreparedStatement statement = null;
        int countInsert = 0;

        try
        {
            connection = Utils.getConnection();

            for(Teacher teacher : teachers)
            {
                statement = connection.prepareStatement(sql);

                statement.setInt(1,teacher.getTeacher_id());
                statement.setString(2,teacher.getName());
                statement.setString(3,teacher.getTeacherURL());

                statement.executeUpdate();

                System.out.println("-----成功插入第 " + (countInsert++) + "条数据-----");
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}
