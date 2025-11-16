package com.wheelymammoth.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @Column(name = "user_id", length = 36)
    private String userId;
    
    @Column(name = "student_id", unique = true, nullable = false, length = 50)
    private String studentId;
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "profile_photo_url", length = 500)
    private String profilePhotoUrl;
    
    @Column(name = "is_driver", nullable = false)
    private boolean isDriver = false;
    
    @Column(name = "rating")
    private double rating = 0.0;
    
    @Column(name = "rating_count")
    private int ratingCount = 0;
    
    @Column(name = "points")
    private int points = 0;
    
    // Driver-specific fields
    @Column(name = "license_image_url", length = 500)
    private String licenseImageUrl;
    
    @Column(name = "student_id_image_url", length = 500)
    private String studentIdImageUrl;
    
    @Column(name = "approved")
    private boolean approved = false;
    
    // Vehicle fields (stored as JSON or separate columns)
    @Column(name = "vehicle_make", length = 50)
    private String vehicleMake;
    
    @Column(name = "vehicle_model", length = 50)
    private String vehicleModel;
    
    @Column(name = "vehicle_year")
    private Integer vehicleYear;
    
    @Column(name = "vehicle_color", length = 30)
    private String vehicleColor;
    
    @Column(name = "vehicle_license_plate", length = 20)
    private String vehicleLicensePlate;
    
    @Column(name = "vehicle_seats")
    private Integer vehicleSeats;
    
    public UserEntity() {
        this.userId = UUID.randomUUID().toString();
    }
    
    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
    
    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
    
    public boolean isDriver() {
        return isDriver;
    }
    
    public void setDriver(boolean driver) {
        isDriver = driver;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public int getRatingCount() {
        return ratingCount;
    }
    
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public String getLicenseImageUrl() {
        return licenseImageUrl;
    }
    
    public void setLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
    }
    
    public String getStudentIdImageUrl() {
        return studentIdImageUrl;
    }
    
    public void setStudentIdImageUrl(String studentIdImageUrl) {
        this.studentIdImageUrl = studentIdImageUrl;
    }
    
    public boolean isApproved() {
        return approved;
    }
    
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
    public String getVehicleMake() {
        return vehicleMake;
    }
    
    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }
    
    public String getVehicleModel() {
        return vehicleModel;
    }
    
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    
    public Integer getVehicleYear() {
        return vehicleYear;
    }
    
    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }
    
    public String getVehicleColor() {
        return vehicleColor;
    }
    
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }
    
    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }
    
    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }
    
    public Integer getVehicleSeats() {
        return vehicleSeats;
    }
    
    public void setVehicleSeats(Integer vehicleSeats) {
        this.vehicleSeats = vehicleSeats;
    }
}

