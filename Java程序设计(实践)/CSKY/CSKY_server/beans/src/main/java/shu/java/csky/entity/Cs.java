package shu.java.csky.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cs")
public class Cs {
    /**
     * csid
     */
    @TableId(type = IdType.AUTO)
    private Integer csid;

    /**
     * 学院代码
     */
    private String collegecode;

    /**
     * 学院名称
     */
    private String collegename;

    /**
     * 专业代码
     */
    private String professioncode;

    /**
     * 专业名称
     */
    private String professionname;

    /**
     * 学习方式
     */
    private String studyway;

    /**
     * 学位类型
     */
    private String degreetype;

    /**
     * 学校id
     */
    private Integer schoolid;

    /**
     * 官网文件链接
     */
    private String articleurl;

    /**
     * 政治分数
     */
    private String politic;

    /**
     * 英语分数
     */
    private String english;

    /**
     * 专业课1分数
     */
    private String professionalSubject1;

    /**
     * 专业课2分数
     */
    private String professionalSubject2;

    /**
     * 总分
     */
    private String total;
}