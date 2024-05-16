package shu.java.csky.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/2/20 23:49
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentParam {
    private Integer userId;
    private Integer articleId;
    private String content;
    private Integer parentId;
    private Integer toUserId;
}
