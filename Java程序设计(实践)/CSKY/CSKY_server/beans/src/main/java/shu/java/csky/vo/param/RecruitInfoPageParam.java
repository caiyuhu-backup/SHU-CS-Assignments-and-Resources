package shu.java.csky.vo.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.entity.Articles;

/**
 * @author: Silence
 * @date: 2022/2/25 15:41
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitInfoPageParam {
    private Integer schoolId;
    private Page<Articles> page;
}
