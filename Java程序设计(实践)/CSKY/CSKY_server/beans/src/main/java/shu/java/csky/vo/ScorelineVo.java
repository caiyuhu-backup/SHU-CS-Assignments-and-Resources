package shu.java.csky.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.entity.Scoreline;

/**
 * @author: Silence
 * @date: 2022/2/26 19:12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScorelineVo {
    private String sname;

    private Integer id;

    private Integer schoolId;

    private String collegeName;

    private String majorCode;

    private String majorName;

    private String majorDir;

    private Integer score21;

    private Integer score20;

    private Integer score19;

    private String englishSubject;

    private String mathSubject;

    private String politicsSubject;

    private String computerSubject;
}
