package com.transportcompany.repository;

import com.transportcompany.model.Company;
import com.transportcompany.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    public void addCompany(Company company) throws SQLException {
        String query = "INSERT INTO companies (name, revenue) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, company.getName());
            stmt.setDouble(2, company.getRevenue());
            stmt.executeUpdate();
        }
    }

    public Company getCompanyById(int id) throws SQLException {
        String query = "SELECT * FROM companies WHERE company_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Company(rs.getInt("company_id"), rs.getString("name"), rs.getDouble("revenue"));
            }
        }
        return null;
    }

    public List<Company> getAllCompanies() throws SQLException {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM companies";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                companies.add(new Company(rs.getInt("company_id"), rs.getString("name"), rs.getDouble("revenue")));
            }
        }
        return companies;
    }

    public void updateCompany(Company company) throws SQLException {
        String query = "UPDATE companies SET name = ?, revenue = ? WHERE company_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, company.getName());
            stmt.setDouble(2, company.getRevenue());
            stmt.setInt(3, company.getCompanyId());
            stmt.executeUpdate();
        }
    }

    public void deleteCompany(int companyId) throws SQLException {
        String query = "DELETE FROM companies WHERE company_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, companyId);
            stmt.executeUpdate();
        }
    }

    public List<Company> getCompaniesSortedByName() throws SQLException {
        String query = "SELECT * FROM companies ORDER BY name";
        List<Company> companies = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                companies.add(new Company(rs.getInt("company_id"), rs.getString("name"), rs.getDouble("revenue")));
            }
        }
        return companies;
    }

    public List<Company> getCompaniesSortedByRevenue() throws SQLException {
        String query = "SELECT * FROM companies ORDER BY revenue DESC";
        List<Company> companies = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                companies.add(new Company(rs.getInt("company_id"), rs.getString("name"), rs.getDouble("revenue")));
            }
        }
        return companies;
    }

}
