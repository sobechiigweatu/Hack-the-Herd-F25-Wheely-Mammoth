package com.wheelymammoth.controller;

import com.wheelymammoth.model.Driver;
import com.wheelymammoth.model.User;
import com.wheelymammoth.model.Vehicle;
import com.wheelymammoth.service.UserService;
import com.wheelymammoth.service.FileUploadService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private FileUploadService fileUploadService;
    
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
        model.addAttribute("user", user);
        return "driver-register";
    }
    
    @PostMapping("/driver-register")
    public String registerDriver(@RequestParam(required = false) MultipartFile licenseImage,
                                @RequestParam(required = false) MultipartFile studentIdImage,
                                @RequestParam(required = false) String licenseImageUrl,
                                @RequestParam(required = false) String studentIdImageUrl,
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
        
        try {
            // Handle file uploads if provided
            String finalLicenseUrl = licenseImageUrl;
            String finalStudentIdUrl = studentIdImageUrl;
            
            if (licenseImage != null && !licenseImage.isEmpty()) {
                finalLicenseUrl = fileUploadService.uploadFile(licenseImage, "licenses");
            }
            if (studentIdImage != null && !studentIdImage.isEmpty()) {
                finalStudentIdUrl = fileUploadService.uploadFile(studentIdImage, "student-ids");
            }
            
            if (finalLicenseUrl == null || finalStudentIdUrl == null) {
                redirectAttributes.addFlashAttribute("error", "Please provide license and student ID images");
                return "redirect:/profile/driver-register";
            }
            
            Vehicle vehicle = new Vehicle(make, model, year, color, licensePlate, seats);
            Driver driver = userService.registerDriver(user.getUserId(), finalLicenseUrl, finalStudentIdUrl, vehicle);
            
            session.setAttribute("user", driver);
            redirectAttributes.addFlashAttribute("success", "Driver registration submitted! Waiting for approval.");
            return "redirect:/profile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error uploading files: " + e.getMessage());
            return "redirect:/profile/driver-register";
        }
    }
}

