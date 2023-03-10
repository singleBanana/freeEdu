package com.freeedu.ordersystemserver.domain;

import java.util.Date;

/**
 * @author Wd
 */
public class Order {
    private Long id;
    private String orderNo;
    private Integer status;
    private Date createTime;

    public Order(Long id, String orderNo, Integer status, Date createTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.status = status;
        this.createTime = createTime;
    }
}
