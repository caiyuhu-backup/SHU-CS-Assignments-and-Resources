package shu.java.csky.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import shu.java.csky.ScoreLineService;
import shu.java.csky.entity.Scoreline;
import shu.java.csky.vo.ResultVO;

import javax.annotation.Resource;

/**
 * @author: Silence
 * @date: 2022/2/25 18:21
 * @description:
 */
@RestController
@RequestMapping("/scoreline")
public class ScorelineController {
    @Resource
    ScoreLineService scoreLineService;

    @GetMapping("school/{sid}")
    ResultVO getScorelineBySid(@PathVariable Integer sid) {
        return scoreLineService.getScorelineBySid(sid);
    }

    @PostMapping("page")
    ResultVO getScorelinePage(@RequestBody Page<Scoreline> scorelineIPage) {
        return scoreLineService.getScorelinePage(scorelineIPage);
    }
}
