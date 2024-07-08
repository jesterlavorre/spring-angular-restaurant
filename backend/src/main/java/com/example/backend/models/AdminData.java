package com.example.backend.models;

import java.util.List;

public class AdminData {
    List<UsersForAdmin> waitingForApproval;
    List<UsersForAdmin> activeUsers;
    List<UsersForAdmin> waiters;
    List<RestaurantsForAdmin> restaurants;

    public AdminData() {
    }

    public AdminData(List<UsersForAdmin> waitingForApproval, List<UsersForAdmin> activeUsers,
            List<UsersForAdmin> waiters, List<RestaurantsForAdmin> restaurants) {
        this.waitingForApproval = waitingForApproval;
        this.activeUsers = activeUsers;
        this.waiters = waiters;
        this.restaurants = restaurants;
    }

    public List<UsersForAdmin> getWaitingForApproval() {
        return waitingForApproval;
    }

    public void setWaitingForApproval(List<UsersForAdmin> waitingForApproval) {
        this.waitingForApproval = waitingForApproval;
    }

    public List<UsersForAdmin> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(List<UsersForAdmin> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public List<UsersForAdmin> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<UsersForAdmin> waiters) {
        this.waiters = waiters;
    }

    public List<RestaurantsForAdmin> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantsForAdmin> restaurants) {
        this.restaurants = restaurants;
    }

}
