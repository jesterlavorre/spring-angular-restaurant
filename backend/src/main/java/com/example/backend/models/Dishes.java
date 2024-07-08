package com.example.backend.models;

import java.util.List;

public class Dishes {
    String id;
    String name;
    String price;
    String picture;
    List<Ingredient> ingredients;

    public Dishes() {
    }

    public Dishes(String id, String name, String price, String picture, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.ingredients = ingredients;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
