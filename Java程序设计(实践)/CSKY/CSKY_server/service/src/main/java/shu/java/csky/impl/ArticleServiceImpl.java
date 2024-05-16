package shu.java.csky.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import shu.java.csky.ArticleService;
import shu.java.csky.CommentService;
import shu.java.csky.UserService;
import shu.java.csky.dao.*;
import shu.java.csky.entity.*;
import shu.java.csky.vo.*;
import shu.java.csky.vo.page.ArticlePageVo;
import shu.java.csky.vo.page.ArticlePageVo2;
import shu.java.csky.vo.page.RecruitInfoPageVo;
import shu.java.csky.vo.param.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/17 13:20
 * @description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticlesMapper articlesMapper;
    @Resource
    TagsMapper tagsMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    LikesMapper likesMapper;
    @Resource
    HistoryMapper historyMapper;
    @Resource
    RepostMapper repostMapper;
    @Resource
    FavoriteMapper favoriteMapper;
    @Resource
    TagNameMapper tagNameMapper;
    @Resource
    CommentService commentService;
    @Resource
    UserService userService;
    @Resource
    SearchMapper searchMapper;

    @Override
    public ResultVO getArticleById(String articleId) {
        return new ResultVO(ResStatus.OK, "文章查询成功", getArticleVo(articleId));
    }

    private ArticleVo getArticleVo(String articleId) {
        QueryWrapper<Articles> articlesQueryWrapper = new QueryWrapper<>();
        articlesQueryWrapper.eq("id", articleId);
        Articles article = articlesMapper.selectOne(articlesQueryWrapper);
        QueryWrapper<Tags> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        List<Tags> tagsList = tagsMapper.selectList(wrapper);
        List<TagName> tagNameList = new ArrayList<>();
        for (Tags tags : tagsList) {
            Integer tagId = tags.getTagId();
            TagName tagName = tagNameMapper.selectById(tagId);
            tagNameList.add(tagName);
        }
        User user = userMapper.selectById(article.getAuthorId());
        UserVo author = new UserVo(user.getUserId(), user.getUsername(), user.getUserImg(), user.getUserMobile(), user.getUserEmail());
        Long likesCount = getLikesCountByAid(Integer.valueOf(articleId));
        Long historyCount = this.getHistoryCountByAid((Integer.valueOf(articleId)));
        Long repostCount = getRepostCountByAid(Integer.valueOf(articleId));
        Long favoriteCountBy = getFavoriteCountByAid((Integer.valueOf(articleId)));
        ArticleVo articleVo = new ArticleVo(likesCount, repostCount, favoriteCountBy, historyCount, tagNameList, author, article);
        return articleVo;
    }

    @Override
    public ResultVO getRecruitInfoBySId(RecruitInfoPageParam articlePageParam) {
        Page<Articles> page = articlePageParam.getPage();
        Integer schoolId = articlePageParam.getSchoolId();
        QueryWrapper<Articles> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", schoolId);
        wrapper.orderByDesc("modify_time");
        articlesMapper.selectPage(page, wrapper);

        RecruitInfoPageVo recruitInfoPageVo = new RecruitInfoPageVo();
        BeanUtils.copyProperties(page, recruitInfoPageVo);
        List<Articles> records = page.getRecords();
        List<RecruitInfoVo> list = new ArrayList<>();
        for (Articles record : records) {
            RecruitInfoVo recruitInfoVo = new RecruitInfoVo(record.getId(), record.getTitle());
            list.add(recruitInfoVo);
        }
        recruitInfoPageVo.setRecruitInfoVoList(list);
        return new ResultVO(ResStatus.OK, "根据学校sid查询招考分页信息成功", recruitInfoPageVo);
    }

    @Override
    public Long getLikesCountByAid(Integer aid) {
        QueryWrapper<Likes> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        return likesMapper.selectCount(wrapper);

    }

    @Override
    public Long getHistoryCountByAid(Integer aid) {
        QueryWrapper<History> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        return historyMapper.selectCount(wrapper);
    }

    @Override
    public Long getRepostCountByAid(Integer aid) {
        QueryWrapper<Repost> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        return repostMapper.selectCount(wrapper);
    }

    @Override
    public Long getFavoriteCountByAid(Integer aid) {
        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        return favoriteMapper.selectCount(wrapper);

    }


    @Override
    public ResultVO insertLikesByAidAndUid(IdParam idParam) {
        Likes likes = new Likes();
        QueryWrapper<Likes> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", idParam.getAid())
                .eq("uid", idParam.getUid());
        Long count = likesMapper.selectCount(wrapper);
        BeanUtils.copyProperties(idParam, likes);
        if (count != 0) {
            return new ResultVO(ResStatus.NO, "该用户已在此文章中点赞过", likes);
        }
        likesMapper.insert(likes);
        ResultVO conditionInfo = getConditionInfo(likes.getAid());
        Object data = conditionInfo.getData();
        return new ResultVO(ResStatus.OK, "点赞插入成功", data);
    }

    @Override
    public ResultVO insertHistoryByAidAndUid(IdParam idParam) {
        History history = new History();
        QueryWrapper<History> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", idParam.getAid())
                .eq("uid", idParam.getUid());
        Long count = historyMapper.selectCount(wrapper);
        if (count != 0) {
            return new ResultVO(ResStatus.NO, "该用户阅读过此文章", history);
        }
        BeanUtils.copyProperties(idParam, history);
        historyMapper.insert(history);
        ResultVO conditionInfo = getConditionInfo(history.getAid());
        Object data = conditionInfo.getData();
        return new ResultVO(ResStatus.OK, "阅读数插入成功", data);
    }

    @Override
    public ResultVO insertRepostByAidAndUid(IdParam idParam) {
        Repost repost = new Repost();
        QueryWrapper<Repost> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", idParam.getAid())
                .eq("uid", idParam.getUid());
        Long count = repostMapper.selectCount(wrapper);
        BeanUtils.copyProperties(idParam, repost);
        if (count != 0) {
            return new ResultVO(ResStatus.NO, "该用户已经转发过此文章", repost);
        }
        repostMapper.insert(repost);
        ResultVO conditionInfo = getConditionInfo(repost.getAid());
        Object data = conditionInfo.getData();
        return new ResultVO(ResStatus.OK, "转发数插入成功", data);
    }

    @Override
    public ResultVO insertFavoriteByAidAndUid(IdParam idParam) {
        Favorite favorite = new Favorite();
        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", idParam.getAid())
                .eq("uid", idParam.getUid());
        Long count = favoriteMapper.selectCount(wrapper);
        BeanUtils.copyProperties(idParam, favorite);
        if (count != 0) {
            return new ResultVO(ResStatus.NO, "该用户已经收藏过改文章", favorite);
        }
        favoriteMapper.insert(favorite);
        ResultVO conditionInfo = getConditionInfo(favorite.getAid());
        Object data = conditionInfo.getData();
        return new ResultVO(ResStatus.OK, "收藏插入成功", data);
    }

    @Override
    public ResultVO insertArticle(ArticleParam articleParam) {
        Articles articles = new Articles();
        BeanUtils.copyProperties(articleParam, articles);
        articles.setCreateTime(new Date());
        articles.setModifyTime(new Date());
        articlesMapper.insert(articles);
        List<Integer> tagIdList = articleParam.getTagIdList();
        System.out.println(tagIdList);
        for (Integer tagId : tagIdList) {
            Tags tags = new Tags();
            tags.setTagId(tagId);
            tags.setArticleId(articles.getId());
            tagsMapper.insert(tags);
        }
        return new ResultVO(ResStatus.OK, "成功", articles);
    }

    @Override
    public ResultVO fuzzyQueryArticle(QueryPageParam queryPageParam) {
        String text = queryPageParam.getText();
        Integer uid = queryPageParam.getUid();
        if (text == null)
            return new ResultVO(ResStatus.OK, "text为空", null);
        QueryWrapper<Articles> wrapper = new QueryWrapper<>();
        wrapper.like("title", text)
                .orderByAsc("id");
        IPage<Articles> articlesPage = new Page<>();
        BeanUtils.copyProperties(queryPageParam, articlesPage);
        articlesMapper.selectPage(articlesPage, wrapper);
        ArticlePageVo articlePageVo = new ArticlePageVo();
        BeanUtils.copyProperties(articlesPage, articlePageVo);
        ArrayList<ArticleBriefParam> articleBriefParams = new ArrayList<>();
        for (Articles record : articlesPage.getRecords()) {
            ArticleBriefParam articleBriefParam = new ArticleBriefParam();
            Integer aid = record.getId();
            Long likesCountByAid = getLikesCountByAid(aid);
            Long commentCount = commentService.getCommentCountByAid(aid);
            Integer authorId = record.getAuthorId();
            String username = userService.getUserById(String.valueOf(authorId)).getUsername();
            BeanUtils.copyProperties(record, articleBriefParam);
            articleBriefParam.setLikeCount(likesCountByAid);
            articleBriefParam.setCommentCount(commentCount);
            articleBriefParam.setAuthorName(username);
            articleBriefParams.add(articleBriefParam);
        }
        articlePageVo.setArticleBriefParams(articleBriefParams);
        // 若查到数据，插入search表
        if (!articleBriefParams.isEmpty() && (uid != null))
            searchMapper.insert(new Search(0, uid, text));
        return new ResultVO(ResStatus.OK, "文章模糊查询分页信息返回成功", articlePageVo);
    }

    @Override
    public ResultVO getConditionByAIdAndUId(Integer aid, Integer uid) {
        ConditionVo conditionVo = new ConditionVo();
        QueryWrapper<Likes> likesQueryWrapper = new QueryWrapper<>();
        likesQueryWrapper.eq("aid", aid)
                .eq("uid", uid);
        Long count1 = likesMapper.selectCount(likesQueryWrapper);
        conditionVo.setIsLiked(count1 != 0);
        QueryWrapper<Favorite> favoriteQueryWrapper = new QueryWrapper<>();
        favoriteQueryWrapper.eq("aid", aid)
                .eq("uid", uid);
        Long count2 = favoriteMapper.selectCount(favoriteQueryWrapper);
        conditionVo.setIsFavorit(count2 != 0);
        QueryWrapper<Repost> repostQueryWrapper = new QueryWrapper<>();
        repostQueryWrapper.eq("aid", aid)
                .eq("uid", uid);
        Long count3 = repostMapper.selectCount(repostQueryWrapper);
        conditionVo.setIsReposted(count3 != 0);

        return new ResultVO(ResStatus.OK, "用户文章状态信息查询成功", conditionVo);
    }

    @Override
    public ResultVO getFavoriteByUId(IdParam idParam) {
        Integer uid = idParam.getUid();
        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        Page<Favorite> page = new Page<>(idParam.getCurrent(), idParam.getSize(), true);
        favoriteMapper.selectPage(page, wrapper);
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Favorite favorite : page.getRecords()) {
            Articles articles = articlesMapper.selectById(favorite.getAid());
            ArticleVo articleVo = getArticleVo(String.valueOf(articles.getId()));
            articleVos.add(articleVo);
        }
        ArticlePageVo2 articlePageVo2 = new ArticlePageVo2();
        BeanUtils.copyProperties(page, articlePageVo2);
        articlePageVo2.setArticleVoList(articleVos);
        return new ResultVO(ResStatus.OK, "收藏文章查询成功", articlePageVo2);
    }

    @Override
    public ResultVO getWorkByUId(IdParam idParam) {
        Integer uid = idParam.getUid();
        QueryWrapper<Articles> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", uid);
        Page<Articles> page = new Page<>(idParam.getCurrent(), idParam.getSize(), true);
        articlesMapper.selectPage(page, wrapper);
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Articles articles : page.getRecords()) {
            ArticleVo articleVo = getArticleVo(String.valueOf(articles.getId()));
            articleVos.add(articleVo);
        }
        ArticlePageVo2 articlePageVo2 = new ArticlePageVo2();
        BeanUtils.copyProperties(page, articlePageVo2);
        articlePageVo2.setArticleVoList(articleVos);
        return new ResultVO(ResStatus.OK, "创建文章查询成功", articlePageVo2);

    }

    @Override
    public ResultVO getConditionInfo(Integer aid) {
        QueryWrapper<Likes> likesQueryWrapper = new QueryWrapper<>();
        likesQueryWrapper.eq("aid", aid);
        QueryWrapper<Repost> repostQueryWrapper = new QueryWrapper<>();
        repostQueryWrapper.eq("aid", aid);
        QueryWrapper<Favorite> favoriteQueryWrapper = new QueryWrapper<>();
        favoriteQueryWrapper.eq("aid", aid);
        Long aLong1 = likesMapper.selectCount(likesQueryWrapper);
        Long aLong2 = repostMapper.selectCount(repostQueryWrapper);
        Long aLong3 = favoriteMapper.selectCount(favoriteQueryWrapper);

        ConditionCountVo conditionCountVo = new ConditionCountVo(aLong1, aLong3, aLong2);
        return new ResultVO(ResStatus.OK, "文章收藏、转发、评论数查询成功", conditionCountVo);
    }

    @Override
    public ResultVO revokeLikesByAidAndUid(IdParam idParam) {
        QueryWrapper<Likes> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", idParam.getAid())
                .eq("uid", idParam.getUid());
        likesMapper.delete(wrapper);
        ResultVO conditionInfo = getConditionInfo(idParam.getAid());
        Object data = conditionInfo.getData();
        return new ResultVO(ResStatus.OK, "点赞删除成功", data);
    }

    @Override
    public ResultVO revokeFavoriteByAidAndUid(IdParam idParam) {
        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", idParam.getAid())
                .eq("uid", idParam.getUid());
        favoriteMapper.delete(wrapper);
        ResultVO conditionInfo = getConditionInfo(idParam.getAid());
        Object data = conditionInfo.getData();
        return new ResultVO(ResStatus.OK, "取消收藏成功", data);
    }

    @Override
    public ResultVO getArticleTitleByTid(Integer tid) {
        QueryWrapper<Tags> tagsQueryWrapper = new QueryWrapper<>();
        tagsQueryWrapper.eq("tag_id", tid)
                .last("LIMIT 10");
        List<Tags> tags = tagsMapper.selectList(tagsQueryWrapper);
        ArrayList<ArticleBriefVo> articleBriefVoArrayList = new ArrayList<>();
        for (Tags tag : tags) {
            Articles articles = articlesMapper.selectById(tag.getArticleId());
            ArticleBriefVo articleBriefVo = new ArticleBriefVo();
            BeanUtils.copyProperties(articles, articleBriefVo);
            articleBriefVoArrayList.add(articleBriefVo);
        }
        return new ResultVO(ResStatus.OK, "根据标签查询文章成功", articleBriefVoArrayList);
    }
}