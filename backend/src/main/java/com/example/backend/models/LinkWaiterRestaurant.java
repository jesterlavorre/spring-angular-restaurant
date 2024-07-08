package com.example.backend.models;

public class LinkWaiterRestaurant {
    String waiterId;
    String restaurantId;

    public LinkWaiterRestaurant() {
    }

    public LinkWaiterRestaurant(String waiterId, String restaurantId) {
        this.waiterId = waiterId;
        this.restaurantId = restaurantId;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
