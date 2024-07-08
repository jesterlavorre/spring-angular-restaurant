package com.example.backend.models;

public class Order {

    private String madeByUsername;
    private String dishId;
    private String amount;
    private String restaurantId;
    private String restaurantName;

    public Order() {
    }

    public Order(String madeByUsername, String dishId, String amount, String restaurantId, String restaurantName) {
        this.madeByUsername = madeByUsername;
        this.dishId = dishId;
        this.amount = amount;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public String getMadeByUsername() {
        return madeByUsername;
    }

    public void setMadeByUsername(String madeByUsername) {
        this.madeByUsername = madeByUsername;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
