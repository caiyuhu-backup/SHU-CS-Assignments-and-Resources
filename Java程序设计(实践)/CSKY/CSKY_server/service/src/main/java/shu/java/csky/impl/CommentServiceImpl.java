package shu.java.csky.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import shu.java.csky.CommentService;
import shu.java.csky.dao.CommentLikesMapper;
import shu.java.csky.dao.CommentMapper;
import shu.java.csky.dao.UserMapper;
import shu.java.csky.entity.Comment;
import shu.java.csky.entity.CommentLikes;
import shu.java.csky.entity.User;
import shu.java.csky.vo.CommentVo;
import shu.java.csky.vo.ResStatus;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.UserVo;
import shu.java.csky.vo.page.CommentPageVo;
import shu.java.csky.vo.param.CommentParam;
import shu.java.csky.vo.param.IdParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/18 9:21
 * @description:
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    CommentLikesMapper commentLikesMapper;

    @Override
    public ResultVO getCommentByArticleId(Integer articleId, Integer userId, Page<Comment> page) {
        // 查询所有记录数
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        Long count = commentMapper.selectCount(wrapper);
        // 查询所有1级评论
        wrapper.eq("level", 1)
                .orderByDesc("praise_num")
                .orderByDesc("create_time");
        commentMapper.selectPage(page, wrapper);
        List<Comment> commentList = page.getRecords();
        List<CommentVo> commentVoList = copyList(commentList, userId);
        CommentPageVo commentPageVo = new CommentPageVo();
        commentPageVo.setCommentCount(count);
        commentPageVo.setCommentVoList(commentVoList);
        BeanUtils.copyProperties(page, commentPageVo);
        return new ResultVO(ResStatus.OK, "评论查询成功", commentPageVo);
    }

    @Override
    public ResultVO addComment(CommentParam commentParam) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParam, comment);
        comment.setCreateTime(new Date());
        Integer parentId = commentParam.getParentId();
        if (parentId == null || parentId == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        comment.setParentId(parentId == null ? 0 : parentId);
        Integer toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentMapper.insert(comment);
        return new ResultVO(ResStatus.OK, "评论插入成功", comment);
    }

    private List<CommentVo> copyList(List<Comment> commentList, Integer userId) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentVoList.add(copy(comment, userId));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment, Integer uid) {
        // 父评论
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        Integer userId = comment.getUserId();
        User user = userMapper.selectById(userId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        commentVo.setAuthor(userVo);
        Integer id = comment.getId();
        // 处理父评论的点赞状态
        Object dataFather = getConditionByAIdAndUId(comment.getId(), uid).getData();
        commentVo.setIsLiked((Boolean) dataFather);
        // 评论的评论
        List<CommentVo> commentVoList = findCommentByParentId(id, uid);
        commentVo.setChildren(commentVoList);
        if (comment.getLevel() > 1) {
            Integer toUid = comment.getToUid();
            User toUser = userMapper.selectById(toUid);
            UserVo toUserVo = new UserVo();
            BeanUtils.copyProperties(toUser, toUserVo);
            commentVo.setToUser(toUserVo);
            Object dataChildren = getConditionByAIdAndUId(comment.getId(), uid).getData();
            commentVo.setIsLiked((Boolean) dataChildren);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentByParentId(Integer id, Integer uid) {
        // 查询所有记录数
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id)
                .eq("level", 2);
        // 查询所有2级评论
        List<Comment> commentList = commentMapper.selectList(wrapper);
        return copyList(commentList, uid);
    }

    @Override
    public ResultVO addLike(IdParam idParam) {
        QueryWrapper<CommentLikes> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id", idParam.getCid())
                .eq("user_id", idParam.getUid());
        Long aLong = commentLikesMapper.selectCount(wrapper);
        if (aLong != 0) {
            return new ResultVO(ResStatus.OK, "该用户已对该评论点赞", null);
        }
        CommentLikes commentLikes = new CommentLikes(0, idParam.getCid(), idParam.getUid());
        commentLikesMapper.insert(commentLikes);
        Comment comment = commentMapper.selectById(idParam.getCid());
        Integer praiseNum = comment.getPraiseNum();
        praiseNum += 1;
        comment.setPraiseNum(praiseNum);
        commentMapper.updateById(comment);
        Long count = getCommentLikesCount(comment.getId());
        return new ResultVO(ResStatus.OK, "评论点赞插入成功", count);
    }

    @Override
    public ResultVO revokeLike(IdParam idParam) {
        QueryWrapper<CommentLikes> commentLikesQueryWrapper = new QueryWrapper<>();
        commentLikesQueryWrapper.eq("comment_id", idParam.getCid())
                .eq("user_id", idParam.getUid());
        commentLikesMapper.delete(commentLikesQueryWrapper);
        Comment comment = commentMapper.selectById(idParam.getCid());
        Integer praiseNum = comment.getPraiseNum();
        praiseNum -= 1;
        comment.setPraiseNum(praiseNum);
        commentMapper.updateById(comment);
        Long count = getCommentLikesCount(idParam.getCid());
        return new ResultVO(ResStatus.OK, "评论删除成功", count);
    }

    public Long getCommentLikesCount(Integer cid) {
        QueryWrapper<CommentLikes> commentLikesQueryWrapper = new QueryWrapper<>();
        commentLikesQueryWrapper.eq("comment_id", cid);
        return commentLikesMapper.selectCount(commentLikesQueryWrapper);
    }

    @Override
    public ResultVO getConditionByAIdAndUId(Integer cid, Integer uid) {
        if (uid == null) {
            return new ResultVO(ResStatus.OK, "uid为空", null);
        }
        QueryWrapper<CommentLikes> commentLikesQueryWrapper = new QueryWrapper<>();
        commentLikesQueryWrapper.eq("comment_id", cid)
                .eq("user_id", uid);
        Long count = commentLikesMapper.selectCount(commentLikesQueryWrapper);
        boolean flag;
        flag = count != 0;

        return new ResultVO(ResStatus.OK, "用户是否点赞信息查询成功", flag);
    }

    @Override
    public Long getCommentCountByAid(Integer aid) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("article_id", aid);
        return commentMapper.selectCount(commentQueryWrapper);
    }
}
