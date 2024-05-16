package shu.java.csky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/2/20 0:26
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVo {
    private Integer id;
    private String tname;
    private String studydir;
}
