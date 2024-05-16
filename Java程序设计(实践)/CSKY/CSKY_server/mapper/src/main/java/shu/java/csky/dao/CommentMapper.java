package shu.java.csky.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shu.java.csky.entity.Comment;
import shu.java.csky.vo.CommentVo;

import java.util.List;

/**
 * @author Silence
 * @description 针对表【comment(文章评论表)】的数据库操作Mapper
 * @createDate 2022-02-17 13:13:57
 * @Entity shu.java.csky.entity.Comment
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> getAllByArticleId(@Param("articleId") String articleId);
}