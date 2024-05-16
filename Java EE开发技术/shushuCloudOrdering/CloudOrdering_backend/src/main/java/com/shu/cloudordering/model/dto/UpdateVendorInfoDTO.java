package com.shu.cloudordering.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/13 16:10
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVendorInfoDTO {
    private Integer vendorId;
    private String vendorName;
    private String vendorImg;
    private String announcement;
    private Integer inBusiness;
}
