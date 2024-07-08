package com.example.backend.models;

public class BasicResponse {
    String status;
    String statusMessage;

    public BasicResponse() {
    }

    public BasicResponse(String status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
