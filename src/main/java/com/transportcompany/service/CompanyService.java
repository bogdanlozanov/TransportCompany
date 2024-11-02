package com.transportcompany.service;

import com.transportcompany.model.Company;
import com.transportcompany.repository.CompanyRepository;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService() {
        this.companyRepository = new CompanyRepository();
    }

    public void addCompany(Company company) throws SQLException {
        if (company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        companyRepository.addCompany(company);
    }

    public Company getCompanyById(int id) throws SQLException {
        return companyRepository.getCompanyById(id);
    }

    public List<Company> getAllCompanies() throws SQLException {
        return companyRepository.getAllCompanies();
    }

    public void updateCompany(Company company) throws SQLException {
        if (company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        companyRepository.updateCompany(company);
    }

    public void deleteCompany(int companyId) throws SQLException {
        companyRepository.deleteCompany(companyId);
    }

    public List<Company> getCompaniesSortedByName() throws SQLException {
        return companyRepository.getCompaniesSortedByName();
    }

    public List<Company> getCompaniesSortedByRevenue() throws SQLException {
        return companyRepository.getCompaniesSortedByRevenue();
    }
}
