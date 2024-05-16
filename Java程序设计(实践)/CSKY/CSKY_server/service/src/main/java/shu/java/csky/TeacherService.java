package shu.java.csky;

import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.param.TeacherPageParam;

/**
 * @author: Silence
 * @date: 2022/2/19 23:47
 * @description:
 */
public interface TeacherService {
    /**
     * 根据学校sid获取教师信息
     *
     * @param sid
     * @return
     */
    ResultVO getTeacherInfoBySId(Integer sid, long currentPage);

    ResultVO getTeacherInfoById(Integer id);

    ResultVO fuzzyQueryTeacher(TeacherPageParam teacherPageParam);
}
