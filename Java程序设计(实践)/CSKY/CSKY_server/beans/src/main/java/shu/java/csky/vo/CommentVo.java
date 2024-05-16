package shu.java.csky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/17 14:11
 * @description: 子查询时Comment映射对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Integer id;

    private UserVo author;

    private String content;

    private List<CommentVo> children;

    private Integer level;

    private Integer praiseNum;

    private Integer topStatus;

    private Date createTime;

    private UserVo toUser;

    private Boolean isLiked;

}
