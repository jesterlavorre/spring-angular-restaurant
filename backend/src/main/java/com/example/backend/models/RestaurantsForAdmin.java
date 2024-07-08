package com.example.backend.models;

import java.util.List;

public class RestaurantsForAdmin {

    private String id;
    private String name;
    private String address;
    private String type;
    private String phoneNumber;
    private String embeddedMap;
    private String maxNumberOfPeople;
    private String opensAt;
    private String closesAt;
    private String layout;
    private String tablesTaken;
    private String shortDescription;

    public RestaurantsForAdmin(String id, String name, String address, String type, String phoneNumber,
            String embeddedMap, String maxNumberOfPeople, String opensAt, String closesAt, String layout,
            String tablesTaken, String shortDescription, List<OperatingHours> operationgHours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.embeddedMap = embeddedMap;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.layout = layout;
        this.tablesTaken = tablesTaken;
        this.shortDescription = shortDescription;
        this.operationgHours = operationgHours;
    }

    private List<OperatingHours> operationgHours;

    public RestaurantsForAdmin(String id, String name, String address, String type, String phoneNumber,
            String embeddedMap, String maxNumberOfPeople, String opensAt, String closesAt, String layout,
            String tablesTaken, List<OperatingHours> operationgHours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.embeddedMap = embeddedMap;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.layout = layout;
        this.tablesTaken = tablesTaken;
        this.operationgHours = operationgHours;
    }

    public RestaurantsForAdmin() {
    }

    public RestaurantsForAdmin(String id, String name, String address, String type, String phoneNumber,
            String embeddedMap, String maxNumberOfPeople, String opensAt, String closesAt, String layout,
            String tablesTaken) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.embeddedMap = embeddedMap;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.layout = layout;
        this.tablesTaken = tablesTaken;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmbeddedMap() {
        return embeddedMap;
    }

    public void setEmbeddedMap(String embeddedMap) {
        this.embeddedMap = embeddedMap;
    }

    public String getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(String maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(String opensAt) {
        this.opensAt = opensAt;
    }

    public String getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(String closesAt) {
        this.closesAt = closesAt;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getTablesTaken() {
        return tablesTaken;
    }

    public void setTablesTaken(String tablesTaken) {
        this.tablesTaken = tablesTaken;
    }

    public List<OperatingHours> getOperationgHours() {
        return operationgHours;
    }

    public void setOperationgHours(List<OperatingHours> operationgHours) {
        this.operationgHours = operationgHours;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
