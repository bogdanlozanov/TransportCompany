package com.transportcompany.model;

import java.util.Date;

public class Payment {
    private int paymentId;
    private Transport transport;
    private Client client;
    private double amount;
    private Date paymentDate;
    private String status;

    public Payment() {}

    public Payment(int paymentId, Transport transport, Client client, double amount, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.transport = transport;
        this.client = client;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
