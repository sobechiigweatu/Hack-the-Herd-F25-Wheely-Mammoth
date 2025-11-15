package com.wheelymammoth.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ride {

    private String rideId;
    private String driverId;
    private Location pickupLocation;
    private Location dropoffLocation;
    private LocalDateTime departureTime;
    private double price;
    private int seatsAvailable;
    private String status; // "open", "full", "completed", "cancelled"

    private List<String> passengerIds;

    public Ride() {}

    public Ride(String driverId, Location pickup, Location dropoff,
                LocalDateTime departureTime, double price, int seatsAvailable) {

        this.rideId = UUID.randomUUID().toString();
        this.driverId = driverId;
        this.pickupLocation = pickup;
        this.dropoffLocation = dropoff;
        this.departureTime = departureTime;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
        this.status = "open";
        this.passengerIds = new ArrayList<>();
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }
    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public Location getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(Location pickupLocation) { this.pickupLocation = pickupLocation; }

    public Location getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(Location dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getPassengerIds() { return passengerIds; }

    // ---------------------
    // Ride logic
    // ---------------------

    public boolean addPassenger(String userId) {
        if (seatsAvailable <= 0) return false;
        passengerIds.add(userId);
        seatsAvailable--;

        if (seatsAvailable == 0) {
            status = "full";
        }
        return true;
    }

    public void removePassenger(String userId) {
        if (passengerIds.remove(userId)) {
            seatsAvailable++;
            if (status.equals("full")) status = "open";
        }
    }

    public void completeRide() {
        this.status = "completed";
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", departureTime=" + departureTime +
                ", price=" + price +
                ", seatsAvailable=" + seatsAvailable +
                '}';
    }
}