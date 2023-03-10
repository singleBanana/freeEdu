package com.freeedu.ordersystemserver.service;

import com.freeedu.ordersystemserver.domain.Order;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {


    public Order findById() {
        return new Order(123L, "orderNo", 1, new Date());
    }

}
