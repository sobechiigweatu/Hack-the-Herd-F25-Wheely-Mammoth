package com.wheelymammoth.controller;

import com.wheelymammoth.model.Ride;
import com.wheelymammoth.model.User;
import com.wheelymammoth.service.RideService;
import com.wheelymammoth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {
    
    @Autowired
    private RideService rideService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Get all available rides (sorted by newest first, like Reddit)
        List<Ride> rides = rideService.getAvailableRides();
        
        // Create a map of rideId to driver name
        Map<String, String> driverNames = new HashMap<>();
        for (Ride ride : rides) {
            User driver = userService.getUserById(ride.getDriverId());
            if (driver != null) {
                driverNames.put(ride.getRideId(), driver.getName());
            }
        }
        
        model.addAttribute("rides", rides);
        model.addAttribute("driverNames", driverNames);
        model.addAttribute("currentUser", user);
        
        return "dashboard";
    }
}

