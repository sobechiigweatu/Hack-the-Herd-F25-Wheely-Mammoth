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
    
    /**
     * Search and filter rides based on various criteria
     * @param keyword Search term for pickup/dropoff addresses or driver name
     * @param minPrice Minimum price filter
     * @param maxPrice Maximum price filter
     * @param minSeats Minimum seats available filter
     * @param maxSeats Maximum seats available filter
     * @param startDate Filter rides after this date
     * @param endDate Filter rides before this date
     * @param userService Service to look up driver names for keyword search
     * @return Filtered list of available rides
     */
    public List<Ride> searchAndFilterRides(String keyword, Double minPrice, Double maxPrice, 
                                          Integer minSeats, Integer maxSeats,
                                          LocalDateTime startDate, LocalDateTime endDate,
                                          com.wheelymammoth.service.UserService userService) {
        List<Ride> rides = getAvailableRides();
        
        // Filter by keyword (search in pickup/dropoff addresses)
        if (keyword != null && !keyword.trim().isEmpty()) {
            String keywordLower = keyword.toLowerCase().trim();
            rides = rides.stream()
                    .filter(r -> 
                        (r.getPickupLocation().getAddress().toLowerCase().contains(keywordLower)) ||
                        (r.getDropoffLocation().getAddress().toLowerCase().contains(keywordLower)) ||
                        (userService != null && searchByDriverName(r, keywordLower, userService))
                    )
                    .collect(Collectors.toList());
        }
        
        // Filter by price range
        if (minPrice != null) {
            rides = rides.stream()
                    .filter(r -> r.getPrice() >= minPrice)
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            rides = rides.stream()
                    .filter(r -> r.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }
        
        // Filter by seats available
        if (minSeats != null) {
            rides = rides.stream()
                    .filter(r -> r.getSeatsAvailable() >= minSeats)
                    .collect(Collectors.toList());
        }
        if (maxSeats != null) {
            rides = rides.stream()
                    .filter(r -> r.getSeatsAvailable() <= maxSeats)
                    .collect(Collectors.toList());
        }
        
        // Filter by date range
        if (startDate != null) {
            rides = rides.stream()
                    .filter(r -> r.getDepartureTime().isAfter(startDate) || r.getDepartureTime().isEqual(startDate))
                    .collect(Collectors.toList());
        }
        if (endDate != null) {
            rides = rides.stream()
                    .filter(r -> r.getDepartureTime().isBefore(endDate) || r.getDepartureTime().isEqual(endDate))
                    .collect(Collectors.toList());
        }
        
        return rides;
    }
    
    private boolean searchByDriverName(Ride ride, String keyword, com.wheelymammoth.service.UserService userService) {
        try {
            com.wheelymammoth.model.User driver = userService.getUserById(ride.getDriverId());
            if (driver != null && driver.getName() != null) {
                return driver.getName().toLowerCase().contains(keyword);
            }
        } catch (Exception e) {
            // Ignore errors in driver lookup
        }
        return false;
    }
}

