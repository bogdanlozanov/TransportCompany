package com.transportcompany.model;

public class Employee {
    private int employeeId;
    private Company company;
    private String name;
    private String qualification;
    private double salary;

    public Employee() {}

    public Employee(int employeeId, Company company, String name, String qualification, double salary) {
        this.employeeId = employeeId;
        this.company = company;
        this.name = name;
        this.qualification = qualification;
        this.salary = salary;
    }

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
