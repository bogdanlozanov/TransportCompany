package com.transportcompany.model;

import java.util.Date;

public class Transport {
    private int transportId;
    private Company company;
    private Client client;
    private Vehicle vehicle;
    private Employee employee;
    private String startLocation;
    private String endLocation;
    private String cargoDescription;
    private double cargoWeight;
    private Date startDate;
    private Date endDate;
    private double price;

    public Transport() {}

    public Transport(int transportId, Company company, Client client, Vehicle vehicle, Employee employee,
                     String startLocation, String endLocation, String cargoDescription, double cargoWeight,
                     Date startDate, Date endDate, double price) {
        this.transportId = transportId;
        this.company = company;
        this.client = client;
        this.vehicle = vehicle;
        this.employee = employee;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.cargoDescription = cargoDescription;
        this.cargoWeight = cargoWeight;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
