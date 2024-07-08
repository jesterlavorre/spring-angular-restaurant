package com.example.backend.models;

import java.util.List;

public class DeliveryOrdersWaiter {
    String orderId;
    String orderedWhen;
    String orderedBy;
    String contactPhone;
    List<OrderDishWaiter> orderDishList;

    public DeliveryOrdersWaiter(String orderId, String orderedWhen, String orderedBy, String contactPhone,
            List<OrderDishWaiter> orderDishList) {
        this.orderId = orderId;
        this.orderedWhen = orderedWhen;
        this.orderedBy = orderedBy;
        this.contactPhone = contactPhone;
        this.orderDishList = orderDishList;
    }

    public DeliveryOrdersWaiter() {
    }

    public DeliveryOrdersWaiter(String orderId, String orderedWhen, String orderedBy, String contactPhone) {
        this.orderId = orderId;
        this.orderedWhen = orderedWhen;
        this.orderedBy = orderedBy;
        this.contactPhone = contactPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderedWhen() {
        return orderedWhen;
    }

    public void setOrderedWhen(String orderedWhen) {
        this.orderedWhen = orderedWhen;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<OrderDishWaiter> getOrderDishList() {
        return orderDishList;
    }

    public void setOrderDishList(List<OrderDishWaiter> orderDishList) {
        this.orderDishList = orderDishList;
    }

}
