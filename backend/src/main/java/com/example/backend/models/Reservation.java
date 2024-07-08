package com.example.backend.models;

public class Reservation {
    String madeBy;
    String forRestaurant;
    String extraComment;
    String numberOfGuests;
    String when;
    String comment;
    String insertTime;
    String address;

    public Reservation() {
    }

    public Reservation(String madeBy, String forRestaurant, String extraComment, String numberOfGuests, String when,
            String comment, String insertTime, String address) {
        this.madeBy = madeBy;
        this.forRestaurant = forRestaurant;
        this.extraComment = extraComment;
        this.numberOfGuests = numberOfGuests;
        this.when = when;
        this.comment = comment;
        this.insertTime = insertTime;
        this.address = address;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getForRestaurant() {
        return forRestaurant;
    }

    public void setForRestaurant(String forRestaurant) {
        this.forRestaurant = forRestaurant;
    }

    public String getExtraComment() {
        return extraComment;
    }

    public void setExtraComment(String extraComment) {
        this.extraComment = extraComment;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
