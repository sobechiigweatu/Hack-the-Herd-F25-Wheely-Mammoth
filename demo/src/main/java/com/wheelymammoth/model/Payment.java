package com.wheelymammoth.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private String paymentId;
    private String payerId;
    private String driverId;
    private double amount;
    private String status; // "initiated", "completed", "failed"
    private LocalDateTime timestamp;

    public Payment() {}

    public Payment(String payerId, String driverId, double amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.payerId = payerId;
        this.driverId = driverId;
        this.amount = amount;
        this.status = "initiated";
        this.timestamp = LocalDateTime.now();
    }

    public String getPaymentId() { return paymentId; }
    public String getPayerId() { return payerId; }
    public String getDriverId() { return driverId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setStatus(String status) {
        this.status = status;
    }
}