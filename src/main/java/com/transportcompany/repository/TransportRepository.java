package com.transportcompany.repository;

import com.transportcompany.model.Transport;
import com.transportcompany.model.Company;
import com.transportcompany.model.Client;
import com.transportcompany.model.Vehicle;
import com.transportcompany.model.Employee;
import com.transportcompany.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportRepository {

    public void addTransport(Transport transport) throws SQLException {
        String query = "INSERT INTO transports (company_id, client_id, vehicle_id, employee_id, start_location, end_location, cargo_description, cargo_weight, start_date, end_date, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transport.getCompany().getCompanyId());
            stmt.setInt(2, transport.getClient().getClientId());
            stmt.setInt(3, transport.getVehicle().getVehicleId());
            stmt.setInt(4, transport.getEmployee().getEmployeeId());
            stmt.setString(5, transport.getStartLocation());
            stmt.setString(6, transport.getEndLocation());
            stmt.setString(7, transport.getCargoDescription());
            stmt.setDouble(8, transport.getCargoWeight());
            stmt.setDate(9, new java.sql.Date(transport.getStartDate().getTime()));
            stmt.setDate(10, new java.sql.Date(transport.getEndDate().getTime()));
            stmt.setDouble(11, transport.getPrice());
            stmt.executeUpdate();
        }
    }

    public Transport getTransportById(int id) throws SQLException {
        String query = "SELECT * FROM transports WHERE transport_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                ClientRepository clientRepo = new ClientRepository();
                VehicleRepository vehicleRepo = new VehicleRepository();
                EmployeeRepository employeeRepo = new EmployeeRepository();

                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                Client client = clientRepo.getClientById(rs.getInt("client_id"));
                Vehicle vehicle = vehicleRepo.getVehicleById(rs.getInt("vehicle_id"));
                Employee employee = employeeRepo.getEmployeeById(rs.getInt("employee_id"));

                return new Transport(rs.getInt("transport_id"), company, client, vehicle, employee,
                        rs.getString("start_location"), rs.getString("end_location"),
                        rs.getString("cargo_description"), rs.getDouble("cargo_weight"),
                        rs.getDate("start_date"), rs.getDate("end_date"), rs.getDouble("price"));
            }
        }
        return null;
    }

    public List<Transport> getAllTransports() throws SQLException {
        List<Transport> transports = new ArrayList<>();
        String query = "SELECT * FROM transports";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                ClientRepository clientRepo = new ClientRepository();
                VehicleRepository vehicleRepo = new VehicleRepository();
                EmployeeRepository employeeRepo = new EmployeeRepository();

                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                Client client = clientRepo.getClientById(rs.getInt("client_id"));
                Vehicle vehicle = vehicleRepo.getVehicleById(rs.getInt("vehicle_id"));
                Employee employee = employeeRepo.getEmployeeById(rs.getInt("employee_id"));

                transports.add(new Transport(rs.getInt("transport_id"), company, client, vehicle, employee,
                        rs.getString("start_location"), rs.getString("end_location"),
                        rs.getString("cargo_description"), rs.getDouble("cargo_weight"),
                        rs.getDate("start_date"), rs.getDate("end_date"), rs.getDouble("price")));
            }
        }
        return transports;
    }

    public void updateTransport(Transport transport) throws SQLException {
        String query = "UPDATE transports SET company_id = ?, client_id = ?, vehicle_id = ?, employee_id = ?, start_location = ?, end_location = ?, cargo_description = ?, cargo_weight = ?, start_date = ?, end_date = ?, price = ? WHERE transport_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transport.getCompany().getCompanyId());
            stmt.setInt(2, transport.getClient().getClientId());
            stmt.setInt(3, transport.getVehicle().getVehicleId());
            stmt.setInt(4, transport.getEmployee().getEmployeeId());
            stmt.setString(5, transport.getStartLocation());
            stmt.setString(6, transport.getEndLocation());
            stmt.setString(7, transport.getCargoDescription());
            stmt.setDouble(8, transport.getCargoWeight());
            stmt.setDate(9, new java.sql.Date(transport.getStartDate().getTime()));
            stmt.setDate(10, new java.sql.Date(transport.getEndDate().getTime()));
            stmt.setDouble(11, transport.getPrice());
            stmt.setInt(12, transport.getTransportId());
            stmt.executeUpdate();
        }
    }

    public void deleteTransport(int transportId) throws SQLException {
        String query = "DELETE FROM transports WHERE transport_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transportId);
            stmt.executeUpdate();
        }
    }

    public List<Transport> getTransportsByDestination(String destination) throws SQLException {
        String query = "SELECT * FROM transports WHERE end_location = ?";
        List<Transport> transports = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, destination);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transports.add(new Transport(
                        rs.getInt("transport_id"),
                        new Company(rs.getInt("company_id")),
                        new Client(rs.getInt("client_id")),
                        new Vehicle(rs.getInt("vehicle_id")),
                        new Employee(rs.getInt("employee_id")),
                        rs.getString("start_location"),
                        rs.getString("end_location"),
                        rs.getString("cargo_description"),
                        rs.getDouble("cargo_weight"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getDouble("price")
                ));

            }
        }
        return transports;
    }
}
