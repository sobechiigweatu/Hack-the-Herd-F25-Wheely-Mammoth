package com.wheelymammoth.controller;

import com.wheelymammoth.model.Driver;
import com.wheelymammoth.model.User;
import com.wheelymammoth.model.Vehicle;
import com.wheelymammoth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String viewProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Reload user to get latest data
        user = userService.getUserById(user.getUserId());
        session.setAttribute("user", user);
        
        model.addAttribute("user", user);
        if (user instanceof Driver) {
            model.addAttribute("driver", (Driver) user);
        }
        return "profile";
    }
    
    @PostMapping("/update")
    public String updateProfile(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String phone,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        user = userService.getUserById(user.getUserId());
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userService.updateUser(user);
        
        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        return "redirect:/profile";
    }
    
    @GetMapping("/driver-register")
    public String driverRegisterPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (user.isDriver()) {
            return "redirect:/profile";
        }
        return "driver-register";
    }
    
    @PostMapping("/driver-register")
    public String registerDriver(@RequestParam String licenseImageUrl,
                                @RequestParam String studentIdImageUrl,
                                @RequestParam String make,
                                @RequestParam String model,
                                @RequestParam int year,
                                @RequestParam String color,
                                @RequestParam String licensePlate,
                                @RequestParam int seats,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        Vehicle vehicle = new Vehicle(make, model, year, color, licensePlate, seats);
        Driver driver = userService.registerDriver(user.getUserId(), licenseImageUrl, studentIdImageUrl, vehicle);
        
        session.setAttribute("user", driver);
        redirectAttributes.addFlashAttribute("success", "Driver registration submitted! Waiting for approval.");
        return "redirect:/profile";
    }
}

