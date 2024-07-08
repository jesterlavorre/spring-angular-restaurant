package com.example.backend.models;

import java.util.List;

public class Restaurant {
    String id;
    String name;
    String address;
    String type;
    String rated;
    String phoneNumber;
    List<User> waiters;
    String embeddedMap;

    public Restaurant() {
    }

    public Restaurant(String id, String name, String address, String type, String rated, String phoneNumber,
            List<User> waiters, String embeddedMap) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.rated = rated;
        this.phoneNumber = phoneNumber;
        this.waiters = waiters;
        this.embeddedMap = embeddedMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<User> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<User> waiters) {
        this.waiters = waiters;
    }

    public String getEmbeddedMap() {
        return embeddedMap;
    }

    public void setEmbeddedMap(String embeddedMap) {
        this.embeddedMap = embeddedMap;
    }

}
