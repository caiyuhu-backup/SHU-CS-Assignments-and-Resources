package shu.java.csky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tags
 */
@TableName(value ="tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tags{
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("tag_id")
    private Integer tagId;

    @TableField(value = "article_id")
    private Integer articleId;
}