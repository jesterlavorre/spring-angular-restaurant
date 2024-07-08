package com.example.backend.models;

public class ReservationsWaiter {
    String id;
    String numberOfGuests;
    String madeWhen;
    String customerExtraComment;
    String arriveWhen;
    String status;

    public ReservationsWaiter() {
    }

    public ReservationsWaiter(String id, String numberOfGuests, String madeWhen, String customerExtraComment,
            String arriveWhen, String status) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.madeWhen = madeWhen;
        this.customerExtraComment = customerExtraComment;
        this.arriveWhen = arriveWhen;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getMadeWhen() {
        return madeWhen;
    }

    public void setMadeWhen(String madeWhen) {
        this.madeWhen = madeWhen;
    }

    public String getCustomerExtraComment() {
        return customerExtraComment;
    }

    public void setCustomerExtraComment(String customerExtraComment) {
        this.customerExtraComment = customerExtraComment;
    }

    public String getArriveWhen() {
        return arriveWhen;
    }

    public void setArriveWhen(String arriveWhen) {
        this.arriveWhen = arriveWhen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
