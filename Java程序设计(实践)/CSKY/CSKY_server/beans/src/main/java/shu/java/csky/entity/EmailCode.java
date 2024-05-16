package shu.java.csky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 20121706
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emailCode")
public class EmailCode {
    @TableId(type = IdType.AUTO)
    private String eid;
    private String email;
    private String code;
    private Date createTime;
}
