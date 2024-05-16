package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.vo.param.ArticleBriefParam;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/25 20:54
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageVo extends Page {
    List<ArticleBriefParam> articleBriefParams;
}
