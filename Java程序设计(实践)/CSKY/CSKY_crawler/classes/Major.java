package src.classes;

/**
 * @author Liu Yuan
 * @create 2022-01-08-13:19
 * @describe 储存专业的信息
 */
public class Major
{
    private String collegeCode;     //学院代码
    private String collegeName;     //学院名称
    private String professionCode;  //专业代码
    private String professionName;  //专业名称
    private String studyWay;        //学习方式：全日制之类
    private String degreeType;      //学位类型
    private int total;              //总分
    private int politic;            //政治分数
    private int english;            //英语分数
    private int pro1;               //科目1
    private int pro2;               //科目2
    private String articleURL;      //官方文件URL

    public Major(String collegeCode, String collegeName, String professionCode, String professionName, String degreeType, int total,int politic, int english, int pro1, int pro2, String articleURL)
    {
        this.collegeCode = collegeCode;
        this.collegeName = collegeName;
        this.professionCode = professionCode;
        this.professionName = professionName;
        this.studyWay = "全日制";
        this.degreeType = degreeType;
        this.total = total;
        this.politic = politic;
        this.english = english;
        this.pro1 = pro1;
        this.pro2 = pro2;
        this.articleURL = articleURL;
    }

    public String getCollegeCode()
    {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode)
    {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName()
    {
        return collegeName;
    }

    public void setCollegeName(String collegeName)
    {
        this.collegeName = collegeName;
    }

    public String getProfessionCode()
    {
        return professionCode;
    }

    public void setProfessionCode(String professionCode)
    {
        this.professionCode = professionCode;
    }

    public String getProfessionName()
    {
        return professionName;
    }

    public void setProfessionName(String professionName)
    {
        this.professionName = professionName;
    }

    public String getStudyWay()
    {
        return studyWay;
    }

    public void setStudyWay(String studyWay)
    {
        this.studyWay = studyWay;
    }

    public String getDegreeType()
    {
        return degreeType;
    }

    public void setDegreeType(String degreeType)
    {
        this.degreeType = degreeType;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getPolitic()
    {
        return politic;
    }

    public void setPolitic(int politic)
    {
        this.politic = politic;
    }

    public int getEnglish()
    {
        return english;
    }

    public void setEnglish(int english)
    {
        this.english = english;
    }

    public int getPro1()
    {
        return pro1;
    }

    public void setPro1(int pro1)
    {
        this.pro1 = pro1;
    }

    public int getPro2()
    {
        return pro2;
    }

    public void setPro2(int pro2)
    {
        this.pro2 = pro2;
    }

    public String getArticleURL()
    {
        return articleURL;
    }

    public void setArticleURL(String articleURL)
    {
        this.articleURL = articleURL;
    }

    @Override
    public String toString()
    {
        return "Major{" +
                "collegeCode='" + collegeCode + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", professionCode='" + professionCode + '\'' +
                ", professionName='" + professionName + '\'' +
                ", studyWay='" + studyWay + '\'' +
                ", degreeType='" + degreeType + '\'' +
                ", total=" + total +
                ", politic=" + politic +
                ", english=" + english +
                ", pro1=" + pro1 +
                ", pro2=" + pro2 +
                ", articleURL='" + articleURL + '\'' +
                '}' + '\n';
    }
}
