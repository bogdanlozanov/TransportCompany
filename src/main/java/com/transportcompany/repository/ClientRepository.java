package com.transportcompany.repository;

import com.transportcompany.model.Client;
import com.transportcompany.model.Company;
import com.transportcompany.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    public void addClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (name, contact_info, company_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getContactInfo());
            stmt.setInt(3, client.getCompany().getCompanyId());
            stmt.executeUpdate();
        }
    }

    public Client getClientById(int id) throws SQLException {
        String query = "SELECT * FROM clients WHERE client_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                return new Client(rs.getInt("client_id"), rs.getString("name"), rs.getString("contact_info"), company);
            }
        }
        return null;
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                clients.add(new Client(rs.getInt("client_id"), rs.getString("name"), rs.getString("contact_info"), company));
            }
        }
        return clients;
    }

    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE clients SET name = ?, contact_info = ?, company_id = ? WHERE client_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getContactInfo());
            stmt.setInt(3, client.getCompany().getCompanyId());
            stmt.setInt(4, client.getClientId());
            stmt.executeUpdate();
        }
    }

    public void deleteClient(int clientId) throws SQLException {
        String query = "DELETE FROM clients WHERE client_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            stmt.executeUpdate();
        }
    }
}
