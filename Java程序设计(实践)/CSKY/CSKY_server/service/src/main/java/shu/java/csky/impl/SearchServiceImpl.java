package shu.java.csky.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import shu.java.csky.SearchService;
import shu.java.csky.dao.SearchMapper;
import shu.java.csky.entity.Search;
import shu.java.csky.vo.ResStatus;
import shu.java.csky.vo.ResultVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/28 14:30
 * @description:
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    SearchMapper searchMapper;

    @Override
    public void saveSearchByUIdAndText(Integer uid, String text) {
        Search search = new Search(0, uid, text);
        searchMapper.insert(search);
    }

    @Override
    public ResultVO getSearchHistoryByUId(Integer uid) {
        QueryWrapper<Search> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).orderByAsc("id").last("LIMIT 10");
        List<Search> searches = searchMapper.selectList(wrapper);
        ArrayList<String> textList = new ArrayList<>();
        for (Search search : searches) {
            textList.add(search.getText());
        }
        return new ResultVO(ResStatus.OK, "搜索历史返回成功", textList);
    }
}
