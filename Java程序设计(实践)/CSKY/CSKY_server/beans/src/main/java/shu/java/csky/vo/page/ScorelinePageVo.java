package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.vo.ScorelineVo;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/25 18:39
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScorelinePageVo extends Page{
    private List<ScorelineVo> scorelineVoList;
}
