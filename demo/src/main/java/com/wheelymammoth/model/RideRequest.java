package com.wheelymammoth.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RideRequest {

    private String requestId;
    private String rideId;
    private String passengerId;
    private String status; // "pending", "accepted", "declined", "cancelled"
    private LocalDateTime timestamp;

    public RideRequest() {}

    public RideRequest(String rideId, String passengerId) {
        this.requestId = UUID.randomUUID().toString();
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.status = "pending";
        this.timestamp = LocalDateTime.now();
    }

    public String getRequestId() { return requestId; }
    public String getRideId() { return rideId; }
    public String getPassengerId() { return passengerId; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setStatus(String status) {
        this.status = status;
    }
}