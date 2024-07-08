package com.example.backend.models;

public class UpdateOrderStatus {
    String status;
    String estimatedDelivery;
    String orderId;

    public UpdateOrderStatus(String status, String estimatedDelivery, String orderId) {
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public UpdateOrderStatus() {
    }

    public UpdateOrderStatus(String status, String estimatedDelivery) {
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
    }
}
