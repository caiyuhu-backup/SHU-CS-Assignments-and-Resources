package shu.java.csky.controller;

import org.springframework.web.bind.annotation.*;
import shu.java.csky.ArticleService;
import shu.java.csky.TagService;
import shu.java.csky.entity.TagName;
import shu.java.csky.vo.ResStatus;
import shu.java.csky.vo.ResultVO;

import javax.annotation.Resource;

/**
 * @author 20121706
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;
    @Resource
    private ArticleService articleService;

    @GetMapping("/list")
    public ResultVO tagNameList() {
        return new ResultVO(ResStatus.OK, "成功", tagService.tagNameList());
    }

    @PostMapping("addTagName")
    public ResultVO addTag(@RequestBody TagName tagName) {
        return tagService.addTagName(tagName.getTagName());
    }

    @GetMapping("article/{tid}")
    public ResultVO getArticleTitleByTid(@PathVariable Integer tid){
        return articleService.getArticleTitleByTid(tid);
    }

}
