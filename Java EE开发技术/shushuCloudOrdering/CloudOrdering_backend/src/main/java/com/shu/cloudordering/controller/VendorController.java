package com.shu.cloudordering.controller;

import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.dto.*;
import com.shu.cloudordering.model.entity.Vendor;
import com.shu.cloudordering.model.vo.last.CategoryInfoVo;
import com.shu.cloudordering.service.VendorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/4/2 20:32
 * @description:
 */
@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Resource
    private VendorService vendorService;

    @PostMapping("/list")
    public Result VendorListByCanteenId(@RequestBody VendorListDTO params) {
        return vendorService.vendorList(params);
    }

    @GetMapping("/briefInfo/{vendorId}")
    public Result vendorBriefInfo(@PathVariable Integer vendorId) {
        return vendorService.getProductInfoByVendorId(vendorId);
    }

    @GetMapping("/fuzzyQuery")
    public Result vendorFuzzyQuery(@RequestBody QueryPageParam params) {
        return vendorService.vendorFuzzyQuery(params);
    }

    @PostMapping("/commentList")
    public Result commentList(@RequestBody CommentPageDTO params) {
        return vendorService.commentList(params);
    }

    @PostMapping("/updateVendorInfo")
    public Result updateVendorInfo(@RequestBody UpdateVendorInfoDTO params) {
        return vendorService.updateVendorInfo(params);
    }

    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody CategoryInfoVo params) {
        return vendorService.addProduct(params);
    }

    @PostMapping("/updateProduct")
    public Result updateProduct(@RequestBody ProductDTO params) {
        return vendorService.updateProduct(params);
    }

    @GetMapping("/category/{vendorId}")
    public Result categoryList(@PathVariable Integer vendorId) {
        return vendorService.categoryList(vendorId);
    }

    @GetMapping("/listall")
    public Result vendorList(){
        List<Vendor> vendorList = vendorService.list();
        return Result.success("查询成功", vendorList);
    }

}
