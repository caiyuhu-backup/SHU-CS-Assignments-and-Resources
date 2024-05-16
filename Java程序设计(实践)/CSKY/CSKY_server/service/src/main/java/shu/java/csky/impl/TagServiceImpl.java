package shu.java.csky.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shu.java.csky.TagService;
import shu.java.csky.dao.TagNameMapper;
import shu.java.csky.dao.TagsMapper;
import shu.java.csky.entity.TagName;
import shu.java.csky.vo.ResStatus;
import shu.java.csky.vo.ResultVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 20121706
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagsMapper tagsMapper;
    @Resource
    private TagNameMapper tagNameMapper;

    @Override
    public List<TagName> tagNameList() {
        return tagNameMapper.selectList(null);
    }

    @Override
    public ResultVO addTagName(String tagName) {
        QueryWrapper<TagName> tagNameQueryWrapper = new QueryWrapper<>();
        tagNameQueryWrapper.eq("tag_name", tagName);
        Long count = tagNameMapper.selectCount(tagNameQueryWrapper);
        if (count > 0) {
            return new ResultVO(ResStatus.NO, "改标签已存在", null);
        }
        TagName tagName1 = new TagName();
        tagName1.setTagName(tagName);
        tagNameMapper.insert(tagName1);
        return new ResultVO(ResStatus.OK, "添加成功", tagName1);
    }
}
