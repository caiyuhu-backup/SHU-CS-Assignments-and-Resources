package shu.java.csky;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shu.java.csky.entity.Comment;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.param.CommentParam;
import shu.java.csky.vo.param.IdParam;

/**
 * @author: Silence
 * @date: 2022/2/18 9:21
 * @description:
 */
public interface CommentService {
    /**
     * 根据文章的Id，获取文章的评论
     *  @param articleId 文章的Id
     * @param userId 用户的Id
     * @param page      分页参数
     */
    ResultVO getCommentByArticleId(Integer articleId, Integer userId, Page<Comment> page);

    ResultVO addComment(CommentParam commentParam);

    ResultVO addLike(IdParam idParam);

    ResultVO revokeLike(IdParam idParam);

    ResultVO getConditionByAIdAndUId(Integer cid, Integer uid);

    Long getCommentCountByAid(Integer aid);
}
