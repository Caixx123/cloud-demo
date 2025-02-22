package com.atguigu.order.controller;

import com.atguigu.order.bean.Order;
import com.atguigu.order.properties.OrderProperties;
import com.atguigu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/api/order")
//@RefreshScope
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/getConfig")
    public String getConfig() {
        return "orderTimeout::" + orderProperties.getTimeout()
                + "orderAutoConfirm::" + orderProperties.getAutoConfirm()
                + "orderDbUrl::" + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId,
                             @RequestParam("userId")Long userId) {
        return orderService.createOrder(productId,userId);
    }
}
