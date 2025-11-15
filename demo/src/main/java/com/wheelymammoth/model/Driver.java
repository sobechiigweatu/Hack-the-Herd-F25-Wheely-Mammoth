package com.wheelymammoth.model;

public class Driver extends User {

    // -------------------
    // Fields
    // -------------------
    private String licenseImageUrl;
    private String studentIdImageUrl;
    private Vehicle vehicle;   // You can make this a separate class
    private boolean approved;

    // -------------------
    // Constructors
    // -------------------

    // Default constructor (for Firebase / serialization)
    public Driver() {
        super();
        this.setDriver(true); // mark as driver in base class
    }

    // Constructor for new drivers (not used directly, drivers are created from existing users)
    public Driver(String studentId, String password, String name, String email, String phone,
                  String licenseImageUrl, String studentIdImageUrl, 
                  Vehicle vehicle) {

        super(studentId, password, name, email, phone);
        this.setDriver(true); // ensures isDriver = true

        this.licenseImageUrl = licenseImageUrl;
        this.studentIdImageUrl = studentIdImageUrl;
        this.vehicle = vehicle;
        this.approved = false;
    }

    // Full constructor (loading from database)
    public Driver(String userId, String studentId, String password, String name, String email, String phone,
                  String profilePhotoUrl, boolean isDriver,
                  double rating, int ratingCount,
                  String licenseImageUrl, String studentIdImageUrl,
                  Vehicle vehicle, boolean approved) {

        super(userId, studentId, password, name, email, phone, profilePhotoUrl, isDriver, rating, ratingCount);
        this.licenseImageUrl = licenseImageUrl;
        this.studentIdImageUrl = studentIdImageUrl;
        this.vehicle = vehicle;
        this.approved = approved;

        // Ensure base class's isDriver is true
        this.setDriver(true);
    }

    // -------------------
    // Getters & Setters
    // -------------------

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    // -------------------
    // Behavior Methods
    // -------------------

    public void submitDocuments(String licenseUrl, String studentIdUrl) {
        this.licenseImageUrl = licenseUrl;
        this.studentIdImageUrl = studentIdUrl;
    }

    public void updateVehicle(Vehicle newVehicle) {
        this.vehicle = newVehicle;
    }

    // -------------------
    // Debug/Display
    // -------------------

    @Override
    public String toString() {
        return "Driver{" +
                "userId='" + getUserId() + '\'' +
                ", name='" + getName() + '\'' +
                ", approved=" + approved +
                ", rating=" + getRating() +
                '}';
    }
}