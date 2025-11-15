package com.wheelymammoth.service;

import com.wheelymammoth.model.User;
import com.wheelymammoth.model.Driver;
import com.wheelymammoth.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    
    // In-memory storage (replace with database in production)
    private final Map<String, User> usersByStudentId = new ConcurrentHashMap<>();
    private final Map<String, User> usersById = new ConcurrentHashMap<>();
    
    public User registerUser(String studentId, String password, String name, String email, String phone) {
        if (usersByStudentId.containsKey(studentId)) {
            throw new IllegalArgumentException("Student ID already registered");
        }
        
        User user = new User(studentId, password, name, email, phone);
        usersByStudentId.put(studentId, user);
        usersById.put(user.getUserId(), user);
        return user;
    }
    
    public User login(String studentId, String password) {
        User user = usersByStudentId.get(studentId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public User getUserById(String userId) {
        return usersById.get(userId);
    }
    
    public User getUserByStudentId(String studentId) {
        return usersByStudentId.get(studentId);
    }
    
    public Driver registerDriver(String userId, String licenseImageUrl, String studentIdImageUrl, Vehicle vehicle) {
        User user = usersById.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        
        Driver driver = new Driver(
            user.getUserId(),
            user.getName(),
            user.getEmail(),
            user.getPhone(),
            user.getProfilePhotoUrl(),
            true,
            user.getRating(),
            user.getRatingCount(),
            licenseImageUrl,
            studentIdImageUrl,
            vehicle,
            false
        );
        driver.setStudentId(user.getStudentId());
        driver.setPassword(user.getPassword());
        
        usersById.put(userId, driver);
        usersByStudentId.put(user.getStudentId(), driver);
        return driver;
    }
    
    public void updateUser(User user) {
        usersById.put(user.getUserId(), user);
        usersByStudentId.put(user.getStudentId(), user);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(usersById.values());
    }
}

