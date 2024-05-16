package shu.java.csky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/2/27 0:08
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionVo {
    private Boolean isLiked;
    private Boolean isFavorit;
    private Boolean isReposted;
}
