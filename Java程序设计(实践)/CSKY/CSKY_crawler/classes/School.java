package src.classes;

/**
 * @author Liu Yuan
 * @create 2022-01-08-13:00
 * @describe 存储学校的基本信息
 */
public class School
{
    //所有学校的网页URL开头相同，schoolPageURL + schoolPageID + .shtml 就是对应学校网页
    private static final String schoolPageURL = "http://www.chinakaoyan.com/graduate/grad/schoolID/";

    private static int school_id = 0;   //给id赋值
    private int id;                     //主表中的学校id与这个对应
    private String name;                //学校名称
    private String schoolPageID;        //学校网页对应的id
    private String URL;                 //学校官网
    private String recruitment;         //招生简章，如果是pdf，那么就用URL:开头，如果是文字，那么直接赋值
    private int major_id;               //对应cs表中的专业id
    private int teacher_id;             //对应teachers表中的id
    private int article_id;             //对应articles表中的id

    public School()
    {
    }

    public School(String name, String schoolPageID)
    {
        this.name = name;
        this.schoolPageID = schoolPageID;
    }

    public School(int id, String name, String URL, String recruitment)
    {
        this.id = ++school_id;
        this.name = name;
        this.URL = URL;
        this.recruitment = recruitment;
    }

    public String getSchoolPageID()
    {
        return schoolPageID;
    }

    public void setSchoolPageID(String schoolPageID)
    {
        this.schoolPageID = schoolPageID;
    }

    public String getURL()
    {
        return URL;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
    }

    public static int getSchool_id()
    {
        return school_id;
    }

    public static void setSchool_id(int school_id)
    {
        School.school_id = school_id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRecruitment()
    {
        return recruitment;
    }

    public void setRecruitment(String recruitment)
    {
        this.recruitment = recruitment;
    }

    public int getMajor_id()
    {
        return major_id;
    }

    public void setMajor_id(int major_id)
    {
        this.major_id = major_id;
    }

    public int getTeacher_id()
    {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id)
    {
        this.teacher_id = teacher_id;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public void setArticle_id(int article_id)
    {
        this.article_id = article_id;
    }

    @Override
    public String toString()
    {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schoolPageID='" + schoolPageID + '\'' +
                ", URL='" + URL + '\'' +
                ", recruitment='" + recruitment + '\'' +
                ", major_id=" + major_id +
                ", teacher_id=" + teacher_id +
                ", article_id=" + article_id +
                '}' + '\n';
    }
}
