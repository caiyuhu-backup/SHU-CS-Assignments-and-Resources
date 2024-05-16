package shu.java.csky.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/2/26 21:04
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPageParam extends QueryPageParam{
    private Integer sid;
}
