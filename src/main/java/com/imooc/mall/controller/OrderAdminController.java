package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderAdminController {
    @Autowired
    private OrderService orderService;
    @GetMapping("admin/order/list")
    @ApiOperation("管理员订单列表")
    public ApiRestResponse listFOrAdmin(@RequestParam Integer pageNum, Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @PostMapping("pay")
    @ApiOperation("支付接口")
    public ApiRestResponse pay(@RequestParam String orderNo) {
        orderService.pay(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("admin/order/delivered")
    @ApiOperation("发货")
    public ApiRestResponse delivered(@RequestParam String orderNo) {
        orderService.delivered(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/order/finish")
    @ApiOperation("完结订单")
    public ApiRestResponse finish(@RequestParam String orderNo) {
        orderService.finish(orderNo );
        return ApiRestResponse.success();
    }
}
