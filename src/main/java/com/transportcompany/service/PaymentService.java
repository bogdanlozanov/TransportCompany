package com.transportcompany.service;

import com.transportcompany.model.Payment;
import com.transportcompany.repository.PaymentRepository;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService() {
        this.paymentRepository = new PaymentRepository();
    }

    public void addPayment(Payment payment) throws SQLException {
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        paymentRepository.addPayment(payment);
    }

    public Payment getPaymentById(int id) throws SQLException {
        return paymentRepository.getPaymentById(id);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentRepository.getAllPayments();
    }

    public void updatePayment(Payment payment) throws SQLException {
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        paymentRepository.updatePayment(payment);
    }

    public void deletePayment(int paymentId) throws SQLException {
        paymentRepository.deletePayment(paymentId);
    }
}
