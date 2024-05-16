package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.vo.TeacherVo;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/20 0:21
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherPageVo extends Page{
    List<TeacherVo> teacherVoList;
}
