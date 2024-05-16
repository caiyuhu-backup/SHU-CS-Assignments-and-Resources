package shu.java.csky;

import shu.java.csky.entity.TagName;
import shu.java.csky.vo.ResultVO;

import java.util.List;

/**
 * @author 20121706
 */
public interface TagService {
    List<TagName> tagNameList();

    ResultVO addTagName(String tagName);
}
