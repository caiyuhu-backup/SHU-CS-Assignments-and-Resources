package shu.java.csky.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 20121706
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolVo {
    /**
     * id
     */
    private Integer sid;

    /**
     * 学校名
     */
    private String sname;

    /**
     * 学校简短介绍
     */
    private String shortIntroduction;

    /**
     * 校徽图片
     */
    private String badgeImg;
}
