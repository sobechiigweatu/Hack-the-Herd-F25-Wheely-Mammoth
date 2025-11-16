package com.wheelymammoth.model;

import java.util.UUID;

public class User {

    // -------------------
    // Fields
    // -------------------
    private String userId;
    private String studentId;     // Student ID for login
    private String password;      // Password for authentication
    private String name;
    private String email;
    private String phone;
    private String profilePhotoUrl;
    private boolean isDriver;
    private double rating;        // average rating
    private int ratingCount;      // number of ratings
    private int points;           // points for rewards (drivers earn points)

    // -------------------
    // Constructors
    // -------------------

    // Required no-arg constructor (important for Firebase / serialization)
    public User() {}

    // Standard constructor for new users
    public User(String studentId, String password, String name, String email, String phone) {
        this.userId = UUID.randomUUID().toString();
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profilePhotoUrl = null;
        this.isDriver = false;
        this.rating = 0.0;
        this.ratingCount = 0;
        this.points = 0;
    }

    // Full constructor for loading from a database
    public User(String userId, String studentId, String password, String name, String email, String phone,
                String profilePhotoUrl, boolean isDriver,
                double rating, int ratingCount) {
        this.userId = userId;
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profilePhotoUrl = profilePhotoUrl;
        this.isDriver = isDriver;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.points = 0;
    }

    // -------------------
    // Getters & Setters
    // -------------------

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

    public void setDriver(boolean driverStatus) {
        this.isDriver = driverStatus;
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
    }
    
    public boolean redeemPoints(int pointsToRedeem) {
        if (this.points >= pointsToRedeem) {
            this.points -= pointsToRedeem;
            return true;
        }
        return false;
    }

    // -------------------
    // Behavior Methods
    // -------------------

    /**
     * Add a rating and update the user's average rating.
     *
     * @param newRating rating from 1–5
     */
    public void addRating(double newRating) {
        if (newRating < 1 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        double total = rating * ratingCount;
        ratingCount++;
        rating = (total + newRating) / ratingCount;
    }

    /**
     * Update the user's profile fields.
     */
    public void updateProfile(String newName, String newPhone, String newPhotoUrl) {
        if (newName != null && !newName.isEmpty()) this.name = newName;
        if (newPhone != null && !newPhone.isEmpty()) this.phone = newPhone;
        if (newPhotoUrl != null && !newPhotoUrl.isEmpty()) this.profilePhotoUrl = newPhotoUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isDriver=" + isDriver +
                ", rating=" + rating +
                '}';
    }
}