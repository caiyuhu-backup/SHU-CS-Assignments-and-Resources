package shu.java.csky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("teachers")
public class Teachers {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 链接导师id
     */
    @TableField("schoolId")
    private Integer schoolId;

    /**
     * 导师姓名
     */
    private String tname;

    /**
     * 导师主页
     */
    private String turl;

    private String temail;

    private String studydir;

    /**
     * 教育简历
     */
    private String eduresume;

    private String proresume;

}