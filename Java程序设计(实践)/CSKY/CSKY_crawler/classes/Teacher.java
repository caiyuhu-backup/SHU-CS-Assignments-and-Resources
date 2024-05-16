package src.classes;

/**
 * @author Liu Yuan
 * @create 2022-01-08-13:24
 * @describe 存储导师信息
 */
public class Teacher
{
    private int teacher_id;         //同一个学校的导师的teacher_id是相同的，方便查找
    private String name;            //导师姓名
    private String teacherURL;      //导师信息网页URL
    private String tel;             //导师电话
    private String fax;             //导师传真
    private String email;           //导师邮箱
    private String studyDirection;  //导师研究方向
    private String eduResume;       //导师教育履历
    private String proResume;       //导师专业履历

    public Teacher(int teacher_id, String name, String teacherURL)
    {
        this.teacher_id = teacher_id;
        this.name = name;
        this.teacherURL = teacherURL;
    }

    public String getTeacherURL()
    {
        return teacherURL;
    }

    public void setTeacherURL(String teacherURL)
    {
        this.teacherURL = teacherURL;
    }

    public int getTeacher_id()
    {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id)
    {
        this.teacher_id = teacher_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStudyDirection()
    {
        return studyDirection;
    }

    public void setStudyDirection(String studyDirection)
    {
        this.studyDirection = studyDirection;
    }

    public String getEduResume()
    {
        return eduResume;
    }

    public void setEduResume(String eduResume)
    {
        this.eduResume = eduResume;
    }

    public String getProResume()
    {
        return proResume;
    }

    public void setProResume(String proResume)
    {
        this.proResume = proResume;
    }

    @Override
    public String toString()
    {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", name='" + name + '\'' +
                ", teacherURL='" + teacherURL + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", studyDirection='" + studyDirection + '\'' +
                ", eduResume='" + eduResume + '\'' +
                ", proResume='" + proResume + '\'' +
                '}' + '\n';
    }
}
