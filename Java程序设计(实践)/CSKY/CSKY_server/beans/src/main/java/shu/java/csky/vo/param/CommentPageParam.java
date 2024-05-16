package shu.java.csky.vo.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.entity.Comment;

/**
 * @author: Silence
 * @date: 2022/2/22 10:09
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPageParam {
    private Integer articleId;
    private Integer userId;
    private Page<Comment> page;
}
