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
@TableName("tagname")
public class TagName {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("tag_name")
    private String tagName;
}
