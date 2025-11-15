package com.wheelymammoth.controller;

import com.wheelymammoth.model.*;
import com.wheelymammoth.service.RideService;
import com.wheelymammoth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rides")
public class RideController {
    
    @Autowired
    private RideService rideService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String viewRides(Model model, HttpSession session) {
        List<Ride> rides = rideService.getAvailableRides();
        
        // Create a map of rideId to driver name for easy access in template
        Map<String, String> driverNames = new HashMap<>();
        for (Ride ride : rides) {
            User driver = userService.getUserById(ride.getDriverId());
            if (driver != null) {
                driverNames.put(ride.getRideId(), driver.getName());
            }
        }
        
        model.addAttribute("rides", rides);
        model.addAttribute("driverNames", driverNames);
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("currentUser", currentUser);
        return "rides";
    }
    
    @GetMapping("/create")
    public String createRidePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (!user.isDriver()) {
            model.addAttribute("error", "You must be a registered driver to create rides");
            return "redirect:/profile/driver-register";
        }
        return "create-ride";
    }
    
    @PostMapping("/create")
    public String createRide(@RequestParam String pickupAddress,
                            @RequestParam double pickupLat,
                            @RequestParam double pickupLng,
                            @RequestParam String dropoffAddress,
                            @RequestParam double dropoffLat,
                            @RequestParam double dropoffLng,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime,
                            @RequestParam double price,
                            @RequestParam int seatsAvailable,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isDriver()) {
            redirectAttributes.addFlashAttribute("error", "You must be a driver to create rides");
            return "redirect:/login";
        }
        
        Location pickup = new Location(pickupLat, pickupLng, pickupAddress);
        Location dropoff = new Location(dropoffLat, dropoffLng, dropoffAddress);
        
        Ride ride = rideService.createRide(user.getUserId(), pickup, dropoff, departureTime, price, seatsAvailable);
        redirectAttributes.addFlashAttribute("success", "Ride created successfully!");
        return "redirect:/rides/my-rides";
    }
    
    @GetMapping("/my-rides")
    public String myRides(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        if (user.isDriver()) {
            List<Ride> rides = rideService.getRidesByDriver(user.getUserId());
            model.addAttribute("rides", rides);
            return "my-rides-driver";
        } else {
            List<RideRequest> requests = rideService.getRequestsByPassenger(user.getUserId());
            model.addAttribute("requests", requests);
            return "my-rides-passenger";
        }
    }
    
    @PostMapping("/request")
    public String requestRide(@RequestParam String rideId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        try {
            rideService.requestRide(rideId, user.getUserId());
            redirectAttributes.addFlashAttribute("success", "Ride request submitted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/rides";
    }
    
    @GetMapping("/{rideId}")
    public String viewRideDetails(@PathVariable String rideId, Model model, HttpSession session) {
        Ride ride = rideService.getRideById(rideId);
        if (ride == null) {
            return "redirect:/rides";
        }
        
        User driver = userService.getUserById(ride.getDriverId());
        model.addAttribute("ride", ride);
        model.addAttribute("driver", driver);
        
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("currentUser", currentUser);
        
        if (currentUser != null && currentUser.isDriver() && currentUser.getUserId().equals(ride.getDriverId())) {
            List<RideRequest> requests = rideService.getRequestsForRide(rideId);
            model.addAttribute("requests", requests);
        }
        
        return "ride-details";
    }
    
    @PostMapping("/request/{requestId}/accept")
    public String acceptRequest(@PathVariable String requestId, RedirectAttributes redirectAttributes) {
        try {
            rideService.acceptRideRequest(requestId);
            redirectAttributes.addFlashAttribute("success", "Ride request accepted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/rides/my-rides";
    }
    
    @PostMapping("/request/{requestId}/decline")
    public String declineRequest(@PathVariable String requestId, RedirectAttributes redirectAttributes) {
        rideService.declineRideRequest(requestId);
        redirectAttributes.addFlashAttribute("success", "Ride request declined");
        return "redirect:/rides/my-rides";
    }
}

