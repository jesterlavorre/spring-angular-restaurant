package com.example.backend.models;

import java.util.List;

public class RestaurantInfo {
    Restaurant restaurant;
    List<Comment> comments;

    public RestaurantInfo() {
    }

    public RestaurantInfo(Restaurant restaurant, List<Comment> comments) {
        this.restaurant = restaurant;
        this.comments = comments;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
