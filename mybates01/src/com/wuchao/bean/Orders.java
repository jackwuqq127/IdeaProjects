package com.wuchao.bean;

import java.util.Date;

public class Orders {
    private int orderId;
    private String orderName;
    private String orderBalance;
    private Date orderDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderBalance() {
        return orderBalance;
    }

    public void setOrderBalance(String orderBalance) {
        this.orderBalance = orderBalance;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderBalance='" + orderBalance + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
