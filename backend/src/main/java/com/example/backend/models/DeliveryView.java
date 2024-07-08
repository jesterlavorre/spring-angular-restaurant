package com.example.backend.models;

public class DeliveryView {
    String restaurantName;
    String deliveryPlaced;
    String status;
    String estimatedDelivery;
    String actualDelivery;

    public DeliveryView() {
    }

    public DeliveryView(String restaurantName, String deliveryPlaced, String status, String estimatedDelivery,
            String actualDelivery) {
        this.restaurantName = restaurantName;
        this.deliveryPlaced = deliveryPlaced;
        this.status = status;
        this.estimatedDelivery = estimatedDelivery;
        this.actualDelivery = actualDelivery;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDeliveryPlaced() {
        return deliveryPlaced;
    }

    public void setDeliveryPlaced(String deliveryPlaced) {
        this.deliveryPlaced = deliveryPlaced;
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

    public String getActualDelivery() {
        return actualDelivery;
    }

    public void setActualDelivery(String actualDelivery) {
        this.actualDelivery = actualDelivery;
    }

}
