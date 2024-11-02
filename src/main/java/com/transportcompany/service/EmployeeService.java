package com.transportcompany.service;

import com.transportcompany.model.Employee;
import com.transportcompany.repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }

    public void addEmployee(Employee employee) throws SQLException {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }
        employeeRepository.addEmployee(employee);
    }

    public Employee getEmployeeById(int id) throws SQLException {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeRepository.getAllEmployees();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }
        employeeRepository.updateEmployee(employee);
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        employeeRepository.deleteEmployee(employeeId);
    }

    public List<Employee> getEmployeesSortedByQualification() throws SQLException {
        return employeeRepository.getEmployeesSortedByQualification();
    }

    public List<Employee> getEmployeesSortedBySalary() throws SQLException {
        return employeeRepository.getEmployeesSortedBySalary();
    }
}
