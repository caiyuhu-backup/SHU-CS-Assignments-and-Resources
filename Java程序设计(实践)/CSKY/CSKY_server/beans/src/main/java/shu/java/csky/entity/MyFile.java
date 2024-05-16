package shu.java.csky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 20121706
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file")
public class MyFile {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("url")
    private String url;

    @TableField("subject_code")
    private String subjectCode;

    @TableField("subject")
    private String subject;

    @TableField("year")
    private Integer year;
}
