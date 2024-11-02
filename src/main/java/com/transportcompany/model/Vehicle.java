package com.transportcompany.model;

public class Vehicle {
    private int vehicleId;
    private Company company;
    private String vehicleType;
    private String model;
    private double capacity;

    public Vehicle() {}

    public Vehicle(int vehicleId, Company company, String vehicleType, String model, double capacity) {
        this.vehicleId = vehicleId;
        this.company = company;
        this.vehicleType = vehicleType;
        this.model = model;
        this.capacity = capacity;
    }

    public Vehicle(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
