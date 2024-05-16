package shu.java.csky.vo.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.entity.Articles;
import shu.java.csky.entity.Comment;

/**
 * @author: Silence
 * @date: 2022/2/22 20:36
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdParam {
    private Integer aid;
    private Integer uid;
    private Integer cid;
    private Long current;
    private Long size;
}
