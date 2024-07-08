package com.example.backend.models;

import java.util.List;

public class Home {
    String numberOfRestaurants;
    String numberOfRegisteredUsers;
    String numberOfWaitingUsers;
    String numberOfReservations24h;
    String numberOfReservations7d;
    String numberOfReservations30d;

    List<Restaurant> restaurants;

    public Home() {
    }

    public Home(String numberOfRestaurants, String numberOfRegisteredUsers, String numberOfWaitingUsers,
            String numberOfReservations24h, String numberOfReservations7d, String numberOfReservations30d,
            List<Restaurant> restaurants) {
        this.numberOfRestaurants = numberOfRestaurants;
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
        this.numberOfWaitingUsers = numberOfWaitingUsers;
        this.numberOfReservations24h = numberOfReservations24h;
        this.numberOfReservations7d = numberOfReservations7d;
        this.numberOfReservations30d = numberOfReservations30d;
        this.restaurants = restaurants;
    }

    public String getNumberOfRestaurants() {
        return numberOfRestaurants;
    }

    public void setNumberOfRestaurants(String numberOfRestaurants) {
        this.numberOfRestaurants = numberOfRestaurants;
    }

    public String getNumberOfRegisteredUsers() {
        return numberOfRegisteredUsers;
    }

    public void setNumberOfRegisteredUsers(String numberOfRegisteredUsers) {
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
    }

    public String getNumberOfWaitingUsers() {
        return numberOfWaitingUsers;
    }

    public void setNumberOfWaitingUsers(String numberOfWaitingUsers) {
        this.numberOfWaitingUsers = numberOfWaitingUsers;
    }

    public String getNumberOfReservations24h() {
        return numberOfReservations24h;
    }

    public void setNumberOfReservations24h(String numberOfReservations24h) {
        this.numberOfReservations24h = numberOfReservations24h;
    }

    public String getNumberOfReservations7d() {
        return numberOfReservations7d;
    }

    public void setNumberOfReservations7d(String numberOfReservations7d) {
        this.numberOfReservations7d = numberOfReservations7d;
    }

    public String getNumberOfReservations30d() {
        return numberOfReservations30d;
    }

    public void setNumberOfReservations30d(String numberOfReservations30d) {
        this.numberOfReservations30d = numberOfReservations30d;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
