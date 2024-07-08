package com.example.backend.models;

public class WorksAtRestaurant {
    String restaurantId;
    String layout;
    String tablesSelected;

    public WorksAtRestaurant(String restaurantId, String layout, String tablesSelected) {
        this.restaurantId = restaurantId;
        this.layout = layout;
        this.tablesSelected = tablesSelected;
    }

    public WorksAtRestaurant() {
    }

    public WorksAtRestaurant(String restaurantId, String layout) {
        this.restaurantId = restaurantId;
        this.layout = layout;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getTablesSelected() {
        return tablesSelected;
    }

    public void setTablesSelected(String tablesSelected) {
        this.tablesSelected = tablesSelected;
    }

}
