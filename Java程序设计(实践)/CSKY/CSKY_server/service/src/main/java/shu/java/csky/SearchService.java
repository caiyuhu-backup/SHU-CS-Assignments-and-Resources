package shu.java.csky;

import shu.java.csky.vo.ResultVO;

/**
 * @author: Silence
 * @date: 2022/2/28 14:28
 * @description:
 */
public interface SearchService {
    void saveSearchByUIdAndText(Integer uid, String text);

    ResultVO getSearchHistoryByUId(Integer uid);
}
