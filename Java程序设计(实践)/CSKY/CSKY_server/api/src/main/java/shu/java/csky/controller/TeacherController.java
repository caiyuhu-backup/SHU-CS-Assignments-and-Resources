package shu.java.csky.controller;

import org.springframework.web.bind.annotation.*;
import shu.java.csky.TeacherService;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.param.TeacherPageParam;

import javax.annotation.Resource;

/**
 * @author: Silence
 * @date: 2022/2/19 23:45
 * @description:
 */

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Resource
    TeacherService teacherService;

    /**
     * 根据学校sid与当前页获取分页信息
     *
     * @param sid         学校sid
     * @param currentPage 当前页
     * @return 包含教师信息，分页信息的ResultVO
     */
    @GetMapping("briefInfo/{sid}/{currentPage}")
    ResultVO getTeacherInfoPage(@PathVariable("sid") Integer sid, @PathVariable("currentPage") long currentPage) {
        if (sid == null || currentPage <= 0) {
            return new ResultVO(400, "参数为空或页码数据错误", null);
        }
        return teacherService.getTeacherInfoBySId(sid, currentPage);
    }

    @GetMapping("detailedInfo/{id}")
    ResultVO getTeacherDetailedInfo(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResultVO(400, "参数不能为空~", null);
        }
        return teacherService.getTeacherInfoById(id);
    }

    @PostMapping("/query")
    public ResultVO fuzzyQueryTeacher(@RequestBody TeacherPageParam teacherPageParam) {
        return teacherService.fuzzyQueryTeacher(teacherPageParam);
    }
}
