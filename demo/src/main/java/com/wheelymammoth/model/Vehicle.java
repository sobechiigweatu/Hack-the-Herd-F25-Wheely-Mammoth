package com.wheelymammoth.model;

public class Vehicle {

    private String make;
    private String model;
    private int year;
    private String color;
    private String licensePlate;
    private int seats;

    public Vehicle() {}

    public Vehicle(String make, String model, int year, String color,
                   String licensePlate, int seats) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.licensePlate = licensePlate;
        this.seats = seats;
    }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }
}