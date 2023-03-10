package com.freeedu.ordersystemserver.controller;


import com.freeedu.commonserver.entity.Resp;
import com.freeedu.ordersystemserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/findById")
    public Resp findById() {
        return Resp.success();
    }
}
