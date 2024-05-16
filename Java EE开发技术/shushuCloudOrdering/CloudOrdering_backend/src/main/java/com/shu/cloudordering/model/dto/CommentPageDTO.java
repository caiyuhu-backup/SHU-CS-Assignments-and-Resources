package com.shu.cloudordering.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.cloudordering.model.entity.ProductComments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/10 0:29
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPageDTO {
    private String vendorId;
    private Page<ProductComments> page;
}
