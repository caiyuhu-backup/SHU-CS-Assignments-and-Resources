package com.shu.cloudordering.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author: Silence
 * @date: 2022/5/9 21:24
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String orderId;
    private String text;
    private Integer grade;
    private ArrayList<String> imgList;
}
