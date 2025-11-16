package com.wheelymammoth.controller;

import com.wheelymammoth.model.User;
import com.wheelymammoth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String studentId, 
                       @RequestParam String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        User user = userService.login(studentId, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
            
            // Redirect to intended page or home based on user type
            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                session.removeAttribute("redirectAfterLogin");
                return "redirect:" + redirectUrl;
            }
            
            // Default redirect based on user type
            if (user.isDriver()) {
                return "redirect:/rides/my-rides";
            } else {
                return "redirect:/rides";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid student ID or password");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@RequestParam String studentId,
                          @RequestParam String password,
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phone,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        try {
            User user = userService.registerUser(studentId, password, name, email, phone);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

