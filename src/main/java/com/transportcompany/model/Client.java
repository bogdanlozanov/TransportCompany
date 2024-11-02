package com.transportcompany.model;

public class Client {
    private int clientId;
    private String name;
    private String contactInfo;
    private Company company;

    public Client() {}

    public Client(int clientId, String name, String contactInfo, Company company) {
        this.clientId = clientId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.company = company;
    }

    public Client(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
