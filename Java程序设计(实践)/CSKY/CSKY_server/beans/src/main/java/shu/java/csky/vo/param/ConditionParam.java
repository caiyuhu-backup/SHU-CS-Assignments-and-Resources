package shu.java.csky.vo.param;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/2/27 0:16
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionParam {
    private Integer aid;
    private Integer uid;
}
