package com.example.backend.models;

public class DiagramPie {
    String waiterName;
    String numberOfGuests;

    public DiagramPie() {
    }

    String waiterId;

    public DiagramPie(String waiterName, String numberOfGuests, String waiterId) {
        this.waiterName = waiterName;
        this.numberOfGuests = numberOfGuests;
        this.waiterId = waiterId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

}
