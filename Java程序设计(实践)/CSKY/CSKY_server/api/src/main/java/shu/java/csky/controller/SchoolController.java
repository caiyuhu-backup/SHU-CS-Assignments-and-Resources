package shu.java.csky.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import shu.java.csky.SchoolService;
import shu.java.csky.entity.School;
import shu.java.csky.vo.ResStatus;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.param.QueryPageParam;

import javax.annotation.Resource;

/**
 * @author 20121706
 */
@RestController
@RequestMapping("school")
public class SchoolController {
    @Resource
    private SchoolService schoolService;

    @PostMapping("/list")
    public ResultVO schoolList(@RequestBody Page<School> pageParam) {
        if (pageParam == null) {
            return new ResultVO(400, "参数不能为空~", null);
        }
        return schoolService.getSchoolVoList(pageParam);
    }

    @GetMapping("/detail/{sid}")
    public ResultVO getSchoolDetail(@PathVariable Integer sid) {
        if (sid == null || sid == 0) {
            return new ResultVO(400, "参数不能为空~", null);
        }
        School school = schoolService.getSchoolDetailBySid(sid);
        return new ResultVO(200, "成功", school);
    }

    @GetMapping("/schoolNameList")
    public ResultVO getSchoolNameList() {
        return new ResultVO(ResStatus.OK, "成功", schoolService.getSchoolNameList());
    }


    @PostMapping("/query")
    public ResultVO fuzzyQuerySchool(@RequestBody QueryPageParam queryPageParam) {
        return schoolService.fuzzyQuerySchool(queryPageParam);
    }
}
