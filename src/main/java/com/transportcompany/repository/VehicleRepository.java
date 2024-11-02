package com.transportcompany.repository;

import com.transportcompany.model.Vehicle;
import com.transportcompany.model.Company;
import com.transportcompany.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    public void addVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (company_id, vehicle_type, model, capacity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicle.getCompany().getCompanyId());
            stmt.setString(2, vehicle.getVehicleType());
            stmt.setString(3, vehicle.getModel());
            stmt.setDouble(4, vehicle.getCapacity());
            stmt.executeUpdate();
        }
    }

    public Vehicle getVehicleById(int id) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                return new Vehicle(rs.getInt("vehicle_id"), company, rs.getString("vehicle_type"),
                        rs.getString("model"), rs.getDouble("capacity"));
            }
        }
        return null;
    }

    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                vehicles.add(new Vehicle(rs.getInt("vehicle_id"), company, rs.getString("vehicle_type"),
                        rs.getString("model"), rs.getDouble("capacity")));
            }
        }
        return vehicles;
    }

    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicles SET company_id = ?, vehicle_type = ?, model = ?, capacity = ? WHERE vehicle_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicle.getCompany().getCompanyId());
            stmt.setString(2, vehicle.getVehicleType());
            stmt.setString(3, vehicle.getModel());
            stmt.setDouble(4, vehicle.getCapacity());
            stmt.setInt(5, vehicle.getVehicleId());
            stmt.executeUpdate();
        }
    }

    public void deleteVehicle(int vehicleId) throws SQLException {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
            stmt.executeUpdate();
        }
    }
}
