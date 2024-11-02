package com.transportcompany.model;

public class Company {
    private int companyId;
    private String name;
    private double revenue;

    public Company() {}

    public Company(int companyId, String name, double revenue) {
        this.companyId = companyId;
        this.name = name;
        this.revenue = revenue;
    }

    public Company(int companyId) {
        this.companyId = companyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
