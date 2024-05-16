package shu.java.csky;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shu.java.csky.entity.Scoreline;
import shu.java.csky.vo.ResultVO;

/**
 * @author: Silence
 * @date: 2022/2/25 18:24
 * @description:
 */
public interface ScoreLineService {
    ResultVO getScorelineBySid(Integer sid);

    ResultVO getScorelinePage(Page<Scoreline> scorelineIPage);
}
