package com.wheelymammoth.service;

import com.wheelymammoth.entity.UserEntity;
import com.wheelymammoth.model.User;
import com.wheelymammoth.model.Driver;
import com.wheelymammoth.model.Vehicle;
import com.wheelymammoth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User registerUser(String studentId, String password, String name, String email, String phone) {
        if (userRepository.existsByStudentId(studentId)) {
            throw new IllegalArgumentException("Student ID already registered");
        }
        
        UserEntity entity = new UserEntity();
        entity.setStudentId(studentId);
        entity.setPassword(password);
        entity.setName(name);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setDriver(false);
        entity.setPoints(0);
        entity.setRating(0.0);
        entity.setRatingCount(0);
        
        entity = userRepository.save(entity);
        return convertToUser(entity);
    }
    
    public User login(String studentId, String password) {
        Optional<UserEntity> entityOpt = userRepository.findByStudentId(studentId);
        if (entityOpt.isPresent()) {
            UserEntity entity = entityOpt.get();
            if (entity.getPassword().equals(password)) {
                return convertToUser(entity);
            }
        }
        return null;
    }
    
    public User getUserById(String userId) {
        Optional<UserEntity> entityOpt = userRepository.findById(userId);
        return entityOpt.map(this::convertToUser).orElse(null);
    }
    
    public User getUserByStudentId(String studentId) {
        Optional<UserEntity> entityOpt = userRepository.findByStudentId(studentId);
        return entityOpt.map(this::convertToUser).orElse(null);
    }
    
    @Transactional
    public Driver registerDriver(String userId, String licenseImageUrl, String studentIdImageUrl, Vehicle vehicle) {
        Optional<UserEntity> entityOpt = userRepository.findById(userId);
        if (entityOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        
        UserEntity entity = entityOpt.get();
        entity.setDriver(true);
        entity.setLicenseImageUrl(licenseImageUrl);
        entity.setStudentIdImageUrl(studentIdImageUrl);
        entity.setApproved(false);
        
        // Store vehicle information
        if (vehicle != null) {
            entity.setVehicleMake(vehicle.getMake());
            entity.setVehicleModel(vehicle.getModel());
            entity.setVehicleYear(vehicle.getYear());
            entity.setVehicleColor(vehicle.getColor());
            entity.setVehicleLicensePlate(vehicle.getLicensePlate());
            entity.setVehicleSeats(vehicle.getSeats());
        }
        
        entity = userRepository.save(entity);
        return convertToDriver(entity);
    }
    
    @Transactional
    public void updateUser(User user) {
        Optional<UserEntity> entityOpt = userRepository.findById(user.getUserId());
        if (entityOpt.isPresent()) {
            UserEntity entity = entityOpt.get();
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entity.setPhone(user.getPhone());
            entity.setProfilePhotoUrl(user.getProfilePhotoUrl());
            entity.setRating(user.getRating());
            entity.setRatingCount(user.getRatingCount());
            entity.setPoints(user.getPoints());
            userRepository.save(entity);
        }
    }
    
    public List<User> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (UserEntity entity : entities) {
            if (entity.isDriver()) {
                users.add(convertToDriver(entity));
            } else {
                users.add(convertToUser(entity));
            }
        }
        return users;
    }
    
    // Conversion methods
    private User convertToUser(UserEntity entity) {
        User user = new User(
            entity.getUserId(),
            entity.getStudentId(),
            entity.getPassword(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getProfilePhotoUrl(),
            entity.isDriver(),
            entity.getRating(),
            entity.getRatingCount()
        );
        user.setPoints(entity.getPoints());
        return user;
    }
    
    private Driver convertToDriver(UserEntity entity) {
        Vehicle vehicle = null;
        if (entity.getVehicleMake() != null) {
            vehicle = new Vehicle(
                entity.getVehicleMake(),
                entity.getVehicleModel(),
                entity.getVehicleYear() != null ? entity.getVehicleYear() : 0,
                entity.getVehicleColor() != null ? entity.getVehicleColor() : "",
                entity.getVehicleLicensePlate() != null ? entity.getVehicleLicensePlate() : "",
                entity.getVehicleSeats() != null ? entity.getVehicleSeats() : 0
            );
        }
        
        Driver driver = new Driver(
            entity.getUserId(),
            entity.getStudentId(),
            entity.getPassword(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getProfilePhotoUrl(),
            entity.isDriver(),
            entity.getRating(),
            entity.getRatingCount(),
            entity.getLicenseImageUrl(),
            entity.getStudentIdImageUrl(),
            vehicle,
            entity.isApproved()
        );
        driver.setPoints(entity.getPoints());
        return driver;
    }
}

