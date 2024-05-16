package shu.java.csky.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shu.java.csky.CommentService;
import shu.java.csky.entity.Comment;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.param.CommentPageParam;
import shu.java.csky.vo.param.CommentParam;
import shu.java.csky.vo.param.IdParam;

import javax.annotation.Resource;

/**
 * @author: Silence
 * @date: 2022/2/18 10:25
 * @description:
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping("/get")
    public ResultVO getCommentByArticleId(@RequestBody CommentPageParam commentPageParam) {
        Integer articleId = commentPageParam.getArticleId();
        Page<Comment> page = commentPageParam.getPage();
        Integer userId = commentPageParam.getUserId();
        return commentService.getCommentByArticleId(articleId, userId, page);
    }

    @PostMapping("/add")
    public ResultVO addComment(@RequestBody CommentParam commentParam) {
        return commentService.addComment(commentParam);
    }

    @PostMapping("/addLike")
    public ResultVO addLike(@RequestBody IdParam idParam) {
        return commentService.addLike(idParam);
    }

    @PostMapping("/revokeLike")
    public ResultVO revokeLike(@RequestBody IdParam idParam) {
        return commentService.revokeLike(idParam);
    }

    @PostMapping("/condition")
    public ResultVO getConditionByAIdAndUId(@RequestBody IdParam idParam) {
        Integer cid = idParam.getCid();
        Integer uid = idParam.getUid();
        return commentService.getConditionByAIdAndUId(cid, uid);
    }
}
