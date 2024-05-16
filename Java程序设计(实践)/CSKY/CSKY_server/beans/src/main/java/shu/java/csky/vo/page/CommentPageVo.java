package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.vo.CommentVo;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/21 23:42
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPageVo extends Page {
    /**
     * 评论数
     */
    private long commentCount;

    private List<CommentVo> commentVoList;

}
