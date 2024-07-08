package com.example.backend.models;

public class DiagramBars {
    String dayName;
    String numberOfReservations;
    String averageTotalGuests;

    public DiagramBars() {
    }

    public DiagramBars(String dayName, String numberOfReservations, String averageTotalGuests) {
        this.dayName = dayName;
        this.numberOfReservations = numberOfReservations;
        this.averageTotalGuests = averageTotalGuests;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(String numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }

    public String getAverageTotalGuests() {
        return averageTotalGuests;
    }

    public void setAverageTotalGuests(String averageTotalGuests) {
        this.averageTotalGuests = averageTotalGuests;
    }
}
