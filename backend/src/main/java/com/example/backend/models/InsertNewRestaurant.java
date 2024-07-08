package com.example.backend.models;

public class InsertNewRestaurant {
    private String shortDescription;
    private String closesAt;
    private String opensAt;
    private String maxNumberOfPeople;
    private String embeddedMap;
    private String phoneNumber;
    private String type;
    private String address;
    private String name;

    public InsertNewRestaurant() {
    }

    public InsertNewRestaurant(String shortDescription, String closesAt, String opensAt, String maxNumberOfPeople,
            String embeddedMap, String phoneNumber, String type, String address, String name) {
        this.shortDescription = shortDescription;
        this.closesAt = closesAt;
        this.opensAt = opensAt;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.embeddedMap = embeddedMap;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.address = address;
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(String closesAt) {
        this.closesAt = closesAt;
    }

    public String getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(String opensAt) {
        this.opensAt = opensAt;
    }

    public String getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(String maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getEmbeddedMap() {
        return embeddedMap;
    }

    public void setEmbeddedMap(String embeddedMap) {
        this.embeddedMap = embeddedMap;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
