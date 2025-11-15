package com.wheelymammoth.service;

import com.wheelymammoth.model.Ride;
import com.wheelymammoth.model.RideRequest;
import com.wheelymammoth.model.Location;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class RideService {
    
    private final Map<String, Ride> rides = new ConcurrentHashMap<>();
    private final Map<String, RideRequest> rideRequests = new ConcurrentHashMap<>();
    
    public Ride createRide(String driverId, Location pickup, Location dropoff,
                          LocalDateTime departureTime, double price, int seatsAvailable) {
        Ride ride = new Ride(driverId, pickup, dropoff, departureTime, price, seatsAvailable);
        rides.put(ride.getRideId(), ride);
        return ride;
    }
    
    public Ride getRideById(String rideId) {
        return rides.get(rideId);
    }
    
    public List<Ride> getAllRides() {
        return new ArrayList<>(rides.values());
    }
    
    public List<Ride> getAvailableRides() {
        return rides.values().stream()
                .filter(r -> "open".equals(r.getStatus()))
                .filter(r -> r.getSeatsAvailable() > 0)
                .filter(r -> r.getDepartureTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Ride::getDepartureTime))
                .collect(Collectors.toList());
    }
    
    public List<Ride> getRidesByDriver(String driverId) {
        return rides.values().stream()
                .filter(r -> r.getDriverId().equals(driverId))
                .sorted(Comparator.comparing(Ride::getDepartureTime).reversed())
                .collect(Collectors.toList());
    }
    
    public RideRequest requestRide(String rideId, String passengerId) {
        Ride ride = rides.get(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride not found");
        }
        
        if (ride.getSeatsAvailable() <= 0) {
            throw new IllegalStateException("No seats available");
        }
        
        RideRequest request = new RideRequest(rideId, passengerId);
        rideRequests.put(request.getRequestId(), request);
        return request;
    }
    
    public void acceptRideRequest(String requestId) {
        RideRequest request = rideRequests.get(requestId);
        if (request == null) {
            throw new IllegalArgumentException("Request not found");
        }
        
        Ride ride = rides.get(request.getRideId());
        if (ride == null) {
            throw new IllegalArgumentException("Ride not found");
        }
        
        if (ride.addPassenger(request.getPassengerId())) {
            request.setStatus("accepted");
        } else {
            request.setStatus("declined");
        }
    }
    
    public void declineRideRequest(String requestId) {
        RideRequest request = rideRequests.get(requestId);
        if (request != null) {
            request.setStatus("declined");
        }
    }
    
    public List<RideRequest> getRequestsForRide(String rideId) {
        return rideRequests.values().stream()
                .filter(r -> r.getRideId().equals(rideId))
                .filter(r -> "pending".equals(r.getStatus()))
                .sorted(Comparator.comparing(RideRequest::getTimestamp))
                .collect(Collectors.toList());
    }
    
    public List<RideRequest> getRequestsByPassenger(String passengerId) {
        return rideRequests.values().stream()
                .filter(r -> r.getPassengerId().equals(passengerId))
                .sorted(Comparator.comparing(RideRequest::getTimestamp).reversed())
                .collect(Collectors.toList());
    }
    
    public void cancelRide(String rideId) {
        Ride ride = rides.get(rideId);
        if (ride != null) {
            ride.setStatus("cancelled");
        }
    }
}

