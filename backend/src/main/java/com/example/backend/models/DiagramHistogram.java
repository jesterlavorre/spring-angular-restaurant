package com.example.backend.models;

public class DiagramHistogram {
    String monthOfYear;
    String averageReservationsForMonth;

    public DiagramHistogram() {
    }

    public DiagramHistogram(String monthOfYear, String averageReservationsForMonth) {
        this.monthOfYear = monthOfYear;
        this.averageReservationsForMonth = averageReservationsForMonth;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(String monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public String getAverageReservationsForMonth() {
        return averageReservationsForMonth;
    }

    public void setAverageReservationsForMonth(String averageReservationsForMonth) {
        this.averageReservationsForMonth = averageReservationsForMonth;
    }

}
