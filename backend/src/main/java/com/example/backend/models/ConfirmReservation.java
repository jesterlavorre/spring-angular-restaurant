package com.example.backend.models;

public class ConfirmReservation {
    String reservatioId;
    String username;
    String denyReason;
    String status;

    public ConfirmReservation() {
    }

    public ConfirmReservation(String reservatioId, String username, String denyReason, String status) {
        this.reservatioId = reservatioId;
        this.username = username;
        this.denyReason = denyReason;
        this.status = status;
    }

    public String getReservatioId() {
        return reservatioId;
    }

    public void setReservatioId(String reservatioId) {
        this.reservatioId = reservatioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDenyReason() {
        return denyReason;
    }

    public void setDenyReason(String denyReason) {
        this.denyReason = denyReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
