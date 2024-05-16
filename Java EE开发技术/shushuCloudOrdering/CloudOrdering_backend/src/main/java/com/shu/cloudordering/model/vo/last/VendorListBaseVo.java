package com.shu.cloudordering.model.vo.last;

import com.shu.cloudordering.model.entity.Vendor;
import com.shu.cloudordering.model.vo.basevo.PageBaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/4/2 20:54
 * @description: 食堂中展示的商家信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListBaseVo extends PageBaseVo {
    public List<Vendor> vendorList;
}
