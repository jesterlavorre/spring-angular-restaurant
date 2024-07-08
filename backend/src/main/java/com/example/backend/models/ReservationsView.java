package com.example.backend.models;

import java.util.List;

public class ReservationsView {
    List<Reservation> activeReservations;
    List<Reservation> finishedReservations;

    public ReservationsView() {
    }

    public ReservationsView(List<Reservation> activeReservations, List<Reservation> finishedReservations) {
        this.activeReservations = activeReservations;
        this.finishedReservations = finishedReservations;
    }

    public List<Reservation> getActiveReservations() {
        return activeReservations;
    }

    public void setActiveReservations(List<Reservation> activeReservations) {
        this.activeReservations = activeReservations;
    }

    public List<Reservation> getFinishedReservations() {
        return finishedReservations;
    }

    public void setFinishedReservations(List<Reservation> finishedReservations) {
        this.finishedReservations = finishedReservations;
    }
}
