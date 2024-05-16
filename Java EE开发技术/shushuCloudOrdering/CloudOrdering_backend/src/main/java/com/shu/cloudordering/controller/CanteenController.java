package com.shu.cloudordering.controller;

import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.entity.Canteen;
import com.shu.cloudordering.service.CanteenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/4/2 18:50
 * @description:
 */
@RestController
@RequestMapping("/canteen")
public class CanteenController {
    @Resource
    private CanteenService canteenService;

    @GetMapping("/list")
    public Result canteenList() {
        List<Canteen> canteenList = canteenService.list();
        return Result.success("餐厅List查询成功", canteenList);
    }
}
