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
@TableName("school")
public class School {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer sid;

    /**
     * 学校名
     */
    private String sname;

    /**
     * 招生简章
     */
    private String recruitment;

    /**
     * 学校简介
     */
    private String introduction;

    /**
     * 学校简短介绍
     */
    private String shortIntroduction;

    /**
     * 校徽图片
     */
    private String badgeImg;

    /**
     * 学校地址
     */
    private String address;

    /**
     * 学校电话
     */
    private String phone;

    /**
     * 学校官网
     */
    private String officialSite;

    /**
     * 研究生院官网
     */
    private String postgraduateSite;

}