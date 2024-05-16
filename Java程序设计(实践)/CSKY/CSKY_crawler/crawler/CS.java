package src.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import src.classes.Major;
import src.classes.School;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
 * @create 2022-01-11-18:08
 * @describe
 */
public class CS
{
    private static Connection connection = null;

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

    public static ArrayList<School> getSchoolID_Name()
    {
        /**
         * @function getSchoolID_Name
         * @param []
         * @return java.util.ArrayList<src.classes.School>
         * @description 将已经保存好的学校id和名字保存到链表中，方便下一步操作
         * @author Liu Yuan
         * @time 13:21
         */
        String sql = "select * from schoolid";

        String school_name = null;
        String school_id = null;
        School school = null;
        ArrayList<School> schools = new ArrayList<>();

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next())
            {
                school_id = resultSet.getString(1);
                school_name = resultSet.getString(2);
                school = new School(school_name, school_id);
                schools.add(school);
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        /*for(School school1 : schools)
        {
            System.out.println(school1);
        }*/
        return schools;
    }

    public static void getCSInfo()
    {
        /**
         * @function getCSInfo
         * @param []
         * @return void
         * @description 从公众号中获取学校的分数线
         * @author Liu Yuan
         * @time 13:22
         * @deprecated 获取到的分数线是国家线，重新获取的信息已经导入到数据库中
         */
        String sql_update = "insert into cs values(null,?,?,null,null,null,?,?,?,?,?)";


        String URL = "https://mp.weixin.qq.com/s/CbPPGwteXj7E373EeAGIiw";   //公众号文章URL
        String school_name = null;                                          //学校名称
        String degree_type = null;                                          //学位类型
        int score_total = 0;                                                //总分线
        int score_100 = 0;                                                  //政治英语线
        int score_above_100 = 0;                                            //专业线
        String articleURL = null;                                           //学校文章URL

        try
        {
            PreparedStatement statement_update = connection.prepareStatement(sql_update);
//            ArrayList<School> schoolInformation = getSchoolID_Name();

            Document document = Jsoup.parse(new URL(URL), 30000);
            Elements elements = document.getElementsByTag("span");
//            System.out.println(elements);

            for (Element element : elements)
            {
                if ((element.text().contains("大学") || element.text().contains("工学") || element.text().contains("软件工程") ||
                        element.text().contains("政治") || element.text().contains("电子信息") || element.text().contains("https") ||
                        element.text().contains("类考生") || element.text().contains("计算机") || element.text().contains("网络空间安全")) &&
                        !(element.text().contains("公布") || element.text().contains("注意") || element.text().contains("集合") ||
                                element.text().contains("考研")))
                {
                    if (element.text().contains("大学"))
                    {
                        System.out.println();
                    }
                    if ((element.text().contains("政治") && (!(element.text().contains("专业课1")) || !(element.text().contains("专业课2")))) ||
                            (element.text().contains("电子信息") && !(element.text().contains("："))))
                    {

                    } else
                    {
                        System.out.println(element.text());
                    }
                }
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

    public static int stringSplit(String origin, String split1, String split2)
    {
        /**
         * @function stringSplit
         * @param [origin, split1, split2]
         * @return int
         * @description 字符串分割，将origin中的内容，以split1和split2作为要获取字符串的开始和结尾，同时将这个字符串转换为int，返回分数
         * @author Liu Yuan
         * @time 13:24
         * @deprecated 获取到的分数线是国家线，重新获取的信息已经导入到数据库中
         */
        String number = "";
//        System.out.println(origin.split(split1)[1].split(split2)[0].split("\040")[0]);
        if (split2 != null)
        {
            for (int i = 0; i < origin.split(split1)[1].split(split2)[0].length(); i++)
            {
                if (origin.split(split1)[1].split(split2)[0].charAt(i) >= '0' && origin.split(split1)[1].split(split2)[0].charAt(i) <= '9')
                {
                    number += origin.split(split1)[1].split(split2)[0].charAt(i);
                }
            }
        }
        //origin.split(split1)[1]
        else
        {
            for (int i = 0; i < origin.split(split1)[1].length(); i++)
            {
                if (origin.split(split1)[1].charAt(i) >= '0' && origin.split(split1)[1].charAt(i) <= '9')
                {
                    number += origin.split(split1)[1].charAt(i);
                }
            }
        }

        return new Integer(number).intValue();
    }

    public static ArrayList<Major> fileRead()
    {
        /**
         * @function fileRead
         * @param []
         * @return java.util.ArrayList<src.classes.Major>
         * @description 将文件中的信息读取出来，同时保存到major链表中
         * @author Liu Yuan
         * @time 13:26
         * @deprecated 获取到的分数线是国家线，重新获取的信息已经导入到数据库中
         */
        ArrayList<Major> majors = new ArrayList<>();
        Major major = null;

        try
        {
            BufferedReader in = new BufferedReader(new FileReader("..\\公众号信息.txt"));
            String read = null;
            String school_name = null;
            String degreeType = null;
            String subjectName = null;
            String articleURL = null;
            int total = 0;
            int politic = 0;
            int english = 0;
            int pro_sub1 = 0;
            int pro_sub2 = 0;

            while ((read = in.readLine()) != null)
            {
                if (read == null)
                {
                    continue;
                }

                if (read.contains("大学"))
                {
                    //结束一轮,初始化所有变量
                    {
                        school_name = null;
                        degreeType = null;
                        total = 0;
                        politic = 0;
                        english = 0;
                        pro_sub1 = 0;
                        pro_sub2 = 0;
                        subjectName = null;
                    }

                    school_name = read;
                }
                if (read.contains("专硕") || read.contains("学硕"))
                {
                    degreeType = read.contains("专硕") ? "专硕" : "学硕";
                    subjectName = read.split(degreeType)[0];
                    if (subjectName.contains("（"))
                    {
                        subjectName = subjectName.split("（")[0];
                    }

                    if (read.contains("总分"))
                    {
                        total = Integer.parseInt(read.split("总分：")[1]);
                    }
                }

                if (read.contains("政治"))
                {
                    politic = stringSplit(read, "政治：", " 外语");
                    english = stringSplit(read, "外语：", "专业课1");
                    pro_sub1 = stringSplit(read, "专业课1：", "专业课2");
                    pro_sub2 = stringSplit(read, "专业课2：", null);

                    /*System.out.println("name: " + school_name);
                    System.out.println("subject name: " + subjectName);
                    System.out.println("type: " + degreeType);
                    System.out.println("total: " + total);
                    System.out.println("politic: " + politic);
                    System.out.println("english: " + english);
                    System.out.println("pro1: " + pro_sub1);
                    System.out.println("pro2: " + pro_sub2);*/

                    major = new Major(null, school_name, null, subjectName, degreeType, total, politic, english, pro_sub1, pro_sub2, null);
                    majors.add(major);
                }

                if (read.contains(".htm"))
                {
                    articleURL = read;
                    for (Major major1 : majors)
                    {
                        if (major1.getArticleURL() == null)
                        {
                            major1.setArticleURL(articleURL);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

//        System.out.println(majors);
        return majors;
    }

    public static void writeInformation()
    {
        /**
         * @function writeInformation
         * @param []
         * @return void
         * @description 将从文件中读取到的数据保存到数据库的cs表中
         * @author Liu Yuan
         * @time 13:26
         * @deprecated 获取到的分数线是国家线，重新获取的信息已经导入到数据库中
         */
        ArrayList<Major> information = fileRead();

        String sql_update = "insert into cs values(null,null,?,null,?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement statement_update = connection.prepareStatement(sql_update);
            for (Major major : information)
            {
                statement_update.setString(1,major.getCollegeName());
                statement_update.setString(2,major.getProfessionName());
                statement_update.setString(3,major.getStudyWay());
                statement_update.setString(4,major.getDegreeType());
                statement_update.setString(5,major.getArticleURL());
                statement_update.setInt(6,major.getPolitic());
                statement_update.setInt(7,major.getEnglish());
                statement_update.setInt(8,major.getPro1());
                statement_update.setInt(9,major.getPro2());
                statement_update.setInt(10,major.getTotal());
                statement_update.executeUpdate();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void setSchoolID()
    {
        /**
         * @function setSchoolID
         * @param []
         * @return void
         * @description 写入学校所在网页的代号以及学校的名称
         * @author Liu Yuan
         * @time 13:27
         */
        ArrayList<School> schoolID_name = getSchoolID_Name();

        String sql_query_cs = "select * from cs";
        String sql_update = "update cs set schoolid = ? where collegename = ?";

        int school_id = 0;
        String school_name = null;

        try
        {
            PreparedStatement statement_query_cs = connection.prepareStatement(sql_query_cs);
            PreparedStatement statement_update = connection.prepareStatement(sql_update);
            ResultSet resultSet_cs = statement_query_cs.executeQuery();

            while(resultSet_cs.next())
            {
                school_name = resultSet_cs.getString(3);
//                System.out.println(school_name);
                for(School school:schoolID_name)
                {
//                    System.out.println(school.getName());
                    if(school_name.compareTo(school.getName()) == 0)
                    {
                        statement_update.setInt(1,new Integer(school.getSchoolPageID()));
                        statement_update.setString(2,school_name);
//                        System.out.println(statement_update);
                        statement_update.executeUpdate();
                    }
                }
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
