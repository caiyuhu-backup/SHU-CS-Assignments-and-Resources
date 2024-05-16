package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.vo.ArticleVo;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/27 0:52
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageVo2 extends Page {
    public ArticlePageVo2(long current, long pages, long size, long total, List<ArticleVo> articleVoList) {
        super(current, pages, size, total);
        this.articleVoList = articleVoList;
    }

    List<ArticleVo> articleVoList;
}
