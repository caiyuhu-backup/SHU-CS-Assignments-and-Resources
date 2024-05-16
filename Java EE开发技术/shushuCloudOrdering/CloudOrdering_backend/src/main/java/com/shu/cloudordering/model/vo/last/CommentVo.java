package com.shu.cloudordering.model.vo.last;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/10 0:33
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Integer score;
    private String userName;
    private String avatar;
    private String comment;
    private Date commentTime;
    private List<String> imgList;
}
