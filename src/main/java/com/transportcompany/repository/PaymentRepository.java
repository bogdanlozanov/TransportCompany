package com.transportcompany.repository;

import com.transportcompany.model.Payment;
import com.transportcompany.model.Transport;
import com.transportcompany.model.Client;
import com.transportcompany.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    public void addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payments (transport_id, client_id, amount, payment_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, payment.getTransport().getTransportId());
            stmt.setInt(2, payment.getClient().getClientId());
            stmt.setDouble(3, payment.getAmount());
            stmt.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            stmt.setString(5, payment.getStatus());
            stmt.executeUpdate();
        }
    }

    public Payment getPaymentById(int id) throws SQLException {
        String query = "SELECT * FROM payments WHERE payment_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TransportRepository transportRepo = new TransportRepository();
                ClientRepository clientRepo = new ClientRepository();

                Transport transport = transportRepo.getTransportById(rs.getInt("transport_id"));
                Client client = clientRepo.getClientById(rs.getInt("client_id"));

                return new Payment(rs.getInt("payment_id"), transport, client,
                        rs.getDouble("amount"), rs.getDate("payment_date"), rs.getString("status"));
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                TransportRepository transportRepo = new TransportRepository();
                ClientRepository clientRepo = new ClientRepository();

                Transport transport = transportRepo.getTransportById(rs.getInt("transport_id"));
                Client client = clientRepo.getClientById(rs.getInt("client_id"));

                payments.add(new Payment(rs.getInt("payment_id"), transport, client,
                        rs.getDouble("amount"), rs.getDate("payment_date"), rs.getString("status")));
            }
        }
        return payments;
    }

    public void updatePayment(Payment payment) throws SQLException {
        String query = "UPDATE payments SET transport_id = ?, client_id = ?, amount = ?, payment_date = ?, status = ? WHERE payment_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, payment.getTransport().getTransportId());
            stmt.setInt(2, payment.getClient().getClientId());
            stmt.setDouble(3, payment.getAmount());
            stmt.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            stmt.setString(5, payment.getStatus());
            stmt.setInt(6, payment.getPaymentId());
            stmt.executeUpdate();
        }
    }

    public void deletePayment(int paymentId) throws SQLException {
        String query = "DELETE FROM payments WHERE payment_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            stmt.executeUpdate();
        }
    }
}
