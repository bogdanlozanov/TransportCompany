package com.transportcompany.repository;

import com.transportcompany.model.Employee;
import com.transportcompany.model.Company;
import com.transportcompany.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (company_id, name, qualification, salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employee.getCompany().getCompanyId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getQualification());
            stmt.setDouble(4, employee.getSalary());
            stmt.executeUpdate();
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT * FROM employees WHERE employee_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                return new Employee(rs.getInt("employee_id"), company, rs.getString("name"),
                        rs.getString("qualification"), rs.getDouble("salary"));
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                CompanyRepository companyRepo = new CompanyRepository();
                Company company = companyRepo.getCompanyById(rs.getInt("company_id"));
                employees.add(new Employee(rs.getInt("employee_id"), company, rs.getString("name"),
                        rs.getString("qualification"), rs.getDouble("salary")));
            }
        }
        return employees;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employees SET company_id = ?, name = ?, qualification = ?, salary = ? WHERE employee_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employee.getCompany().getCompanyId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getQualification());
            stmt.setDouble(4, employee.getSalary());
            stmt.setInt(5, employee.getEmployeeId());
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        String query = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    public List<Employee> getEmployeesSortedByQualification() throws SQLException {
        String query = "SELECT * FROM employees ORDER BY qualification";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("employee_id"), new Company(rs.getInt("company_id")), rs.getString("name"),
                        rs.getString("qualification"), rs.getDouble("salary")));
            }
        }
        return employees;
    }

    public List<Employee> getEmployeesSortedBySalary() throws SQLException {
        String query = "SELECT * FROM employees ORDER BY salary DESC";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("employee_id"), new Company(rs.getInt("company_id")), rs.getString("name"),
                        rs.getString("qualification"), rs.getDouble("salary")));
            }
        }
        return employees;
    }

}
