package com.transportcompany;

import com.transportcompany.model.*;
import com.transportcompany.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CompanyService companyService = new CompanyService();
    private static final ClientService clientService = new ClientService();
    private static final VehicleService vehicleService = new VehicleService();
    private static final EmployeeService employeeService = new EmployeeService();
    private static final TransportService transportService = new TransportService();
    private static final PaymentService paymentService = new PaymentService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nTransportation Company Management System");
            System.out.println("1. Company Management");
            System.out.println("2. Client Management");
            System.out.println("3. Vehicle Management");
            System.out.println("4. Employee Management");
            System.out.println("5. Transport Management");
            System.out.println("6. Payment Management");
            System.out.println("7. Data Export/Import");
            System.out.println("8. Reports");
            System.out.println("9. Sorting and Filtering");
            System.out.println("10. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> manageCompanies();
                case 2 -> manageClients();
                case 3 -> manageVehicles();
                case 4 -> manageEmployees();
                case 5 -> manageTransports();
                case 6 -> managePayments();
                case 7 -> dataExportImport();
                case 8 -> generateReports();
                case 9 -> sortingAndFiltering();  // Call the sorting and filtering menu
                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void manageCompanies() {
        System.out.println("\nCompany Management");
        System.out.println("1. Add Company");
        System.out.println("2. View All Companies");
        System.out.println("3. View Company by ID");
        System.out.println("4. Update Company");
        System.out.println("5. Delete Company");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> addCompany();
            case 2 -> viewAllCompanies();
            case 3 -> viewCompanyById();
            case 4 -> updateCompany();
            case 5 -> deleteCompany();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addCompany() {
        System.out.print("Enter company name: ");
        String name = scanner.nextLine();
        System.out.print("Enter revenue: ");
        double revenue = scanner.nextDouble();

        Company company = new Company();
        company.setName(name);
        company.setRevenue(revenue);

        try {
            companyService.addCompany(company);
            System.out.println("Company added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding company: " + e.getMessage());
        }
    }

    private static void viewAllCompanies() {
        try {
            List<Company> companies = companyService.getAllCompanies();
            System.out.println("\nList of Companies:");
            for (Company company : companies) {
                System.out.printf("ID: %d, Name: %s, Revenue: %.2f\n",
                        company.getCompanyId(), company.getName(), company.getRevenue());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving companies: " + e.getMessage());
        }
    }

    private static void viewCompanyById() {
        System.out.print("Enter company ID: ");
        int id = scanner.nextInt();
        try {
            Company company = companyService.getCompanyById(id);
            if (company != null) {
                System.out.printf("ID: %d, Name: %s, Revenue: %.2f\n",
                        company.getCompanyId(), company.getName(), company.getRevenue());
            } else {
                System.out.println("Company not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving company: " + e.getMessage());
        }
    }

    private static void updateCompany() {
        System.out.print("Enter company ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            Company company = companyService.getCompanyById(id);
            if (company == null) {
                System.out.println("Company not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new revenue: ");
            double revenue = scanner.nextDouble();

            company.setName(name);
            company.setRevenue(revenue);

            companyService.updateCompany(company);
            System.out.println("Company updated successfully!");

        } catch (SQLException e) {
            System.out.println("Error updating company: " + e.getMessage());
        }
    }

    private static void deleteCompany() {
        System.out.print("Enter company ID: ");
        int id = scanner.nextInt();
        try {
            companyService.deleteCompany(id);
            System.out.println("Company deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting company: " + e.getMessage());
        }
    }

    // Similar structure for manageClients(), manageVehicles(), manageEmployees(), manageTransports(), managePayments()

    private static void manageClients() {
        System.out.println("\nClient Management");
        System.out.println("1. Add Client");
        System.out.println("2. View All Clients");
        System.out.println("3. View Client by ID");
        System.out.println("4. Update Client");
        System.out.println("5. Delete Client");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> addClient();
            case 2 -> viewAllClients();
            case 3 -> viewClientById();
            case 4 -> updateClient();
            case 5 -> deleteClient();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact information: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter associated company ID: ");
        int companyId = scanner.nextInt();

        try {
            Company company = companyService.getCompanyById(companyId);
            if (company == null) {
                System.out.println("Company not found.");
                return;
            }

            Client client = new Client();
            client.setName(name);
            client.setContactInfo(contactInfo);
            client.setCompany(company);

            clientService.addClient(client);
            System.out.println("Client added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    private static void viewAllClients() {
        try {
            List<Client> clients = clientService.getAllClients();
            System.out.println("\nList of Clients:");
            for (Client client : clients) {
                System.out.printf("ID: %d, Name: %s, Contact Info: %s, Company: %s\n",
                        client.getClientId(), client.getName(), client.getContactInfo(),
                        client.getCompany().getName());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving clients: " + e.getMessage());
        }
    }

    private static void viewClientById() {
        System.out.print("Enter client ID: ");
        int id = scanner.nextInt();
        try {
            Client client = clientService.getClientById(id);
            if (client != null) {
                System.out.printf("ID: %d, Name: %s, Contact Info: %s, Company: %s\n",
                        client.getClientId(), client.getName(), client.getContactInfo(),
                        client.getCompany().getName());
            } else {
                System.out.println("Client not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving client: " + e.getMessage());
        }
    }

    private static void updateClient() {
        System.out.print("Enter client ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            Client client = clientService.getClientById(id);
            if (client == null) {
                System.out.println("Client not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new contact information: ");
            String contactInfo = scanner.nextLine();

            client.setName(name);
            client.setContactInfo(contactInfo);

            clientService.updateClient(client);
            System.out.println("Client updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    private static void deleteClient() {
        System.out.print("Enter client ID: ");
        int id = scanner.nextInt();
        try {
            clientService.deleteClient(id);
            System.out.println("Client deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }

    private static void manageVehicles() {
        System.out.println("\nVehicle Management");
        System.out.println("1. Add Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. View Vehicle by ID");
        System.out.println("4. Update Vehicle");
        System.out.println("5. Delete Vehicle");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> addVehicle();
            case 2 -> viewAllVehicles();
            case 3 -> viewVehicleById();
            case 4 -> updateVehicle();
            case 5 -> deleteVehicle();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addVehicle() {
        System.out.print("Enter vehicle type (e.g., bus, truck): ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle capacity: ");
        double capacity = scanner.nextDouble();
        System.out.print("Enter associated company ID: ");
        int companyId = scanner.nextInt();

        try {
            Company company = companyService.getCompanyById(companyId);
            if (company == null) {
                System.out.println("Company not found.");
                return;
            }

            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setModel(model);
            vehicle.setCapacity(capacity);
            vehicle.setCompany(company);

            vehicleService.addVehicle(vehicle);
            System.out.println("Vehicle added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }

    private static void viewAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            System.out.println("\nList of Vehicles:");
            for (Vehicle vehicle : vehicles) {
                System.out.printf("ID: %d, Type: %s, Model: %s, Capacity: %.2f, Company: %s\n",
                        vehicle.getVehicleId(), vehicle.getVehicleType(), vehicle.getModel(),
                        vehicle.getCapacity(), vehicle.getCompany().getName());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving vehicles: " + e.getMessage());
        }
    }

    private static void viewVehicleById() {
        System.out.print("Enter vehicle ID: ");
        int id = scanner.nextInt();
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            if (vehicle != null) {
                System.out.printf("ID: %d, Type: %s, Model: %s, Capacity: %.2f, Company: %s\n",
                        vehicle.getVehicleId(), vehicle.getVehicleType(), vehicle.getModel(),
                        vehicle.getCapacity(), vehicle.getCompany().getName());
            } else {
                System.out.println("Vehicle not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving vehicle: " + e.getMessage());
        }
    }

    private static void updateVehicle() {
        System.out.print("Enter vehicle ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter new vehicle type: ");
            String vehicleType = scanner.nextLine();
            System.out.print("Enter new model: ");
            String model = scanner.nextLine();
            System.out.print("Enter new capacity: ");
            double capacity = scanner.nextDouble();

            vehicle.setVehicleType(vehicleType);
            vehicle.setModel(model);
            vehicle.setCapacity(capacity);

            vehicleService.updateVehicle(vehicle);
            System.out.println("Vehicle updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating vehicle: " + e.getMessage());
        }
    }

    private static void deleteVehicle() {
        System.out.print("Enter vehicle ID: ");
        int id = scanner.nextInt();
        try {
            vehicleService.deleteVehicle(id);
            System.out.println("Vehicle deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting vehicle: " + e.getMessage());
        }
    }

    private static void manageEmployees() {
        System.out.println("\nEmployee Management");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. View Employee by ID");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> addEmployee();
            case 2 -> viewAllEmployees();
            case 3 -> viewEmployeeById();
            case 4 -> updateEmployee();
            case 5 -> deleteEmployee();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter qualification: ");
        String qualification = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Enter associated company ID: ");
        int companyId = scanner.nextInt();

        try {
            Company company = companyService.getCompanyById(companyId);
            if (company == null) {
                System.out.println("Company not found.");
                return;
            }

            Employee employee = new Employee();
            employee.setName(name);
            employee.setQualification(qualification);
            employee.setSalary(salary);
            employee.setCompany(company);

            employeeService.addEmployee(employee);
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    private static void viewAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            System.out.println("\nList of Employees:");
            for (Employee employee : employees) {
                System.out.printf("ID: %d, Name: %s, Qualification: %s, Salary: %.2f, Company: %s\n",
                        employee.getEmployeeId(), employee.getName(), employee.getQualification(),
                        employee.getSalary(), employee.getCompany().getName());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employees: " + e.getMessage());
        }
    }

    private static void viewEmployeeById() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                System.out.printf("ID: %d, Name: %s, Qualification: %s, Salary: %.2f, Company: %s\n",
                        employee.getEmployeeId(), employee.getName(), employee.getQualification(),
                        employee.getSalary(), employee.getCompany().getName());
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee: " + e.getMessage());
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
                System.out.println("Employee not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new qualification: ");
            String qualification = scanner.nextLine();
            System.out.print("Enter new salary: ");
            double salary = scanner.nextDouble();

            employee.setName(name);
            employee.setQualification(qualification);
            employee.setSalary(salary);

            employeeService.updateEmployee(employee);
            System.out.println("Employee updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        try {
            employeeService.deleteEmployee(id);
            System.out.println("Employee deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    private static void manageTransports() {
        System.out.println("\nTransport Management");
        System.out.println("1. Add Transport");
        System.out.println("2. View All Transports");
        System.out.println("3. View Transport by ID");
        System.out.println("4. Update Transport");
        System.out.println("5. Delete Transport");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> addTransport();
            case 2 -> viewAllTransports();
            case 3 -> viewTransportById();
            case 4 -> updateTransport();
            case 5 -> deleteTransport();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addTransport() {
        System.out.print("Enter company ID: ");
        int companyId = scanner.nextInt();
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter start location: ");
        String startLocation = scanner.nextLine();
        System.out.print("Enter end location: ");
        String endLocation = scanner.nextLine();
        System.out.print("Enter cargo description: ");
        String cargoDescription = scanner.nextLine();
        System.out.print("Enter cargo weight: ");
        double cargoWeight = scanner.nextDouble();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter start date (YYYY-MM-DD): ");
        Date startDate = java.sql.Date.valueOf(scanner.nextLine());
        System.out.print("Enter end date (YYYY-MM-DD): ");
        Date endDate = java.sql.Date.valueOf(scanner.nextLine());

        try {
            Company company = companyService.getCompanyById(companyId);
            Client client = clientService.getClientById(clientId);
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            Employee employee = employeeService.getEmployeeById(employeeId);

            if (company == null || client == null || vehicle == null || employee == null) {
                System.out.println("Error: One or more related entities not found.");
                return;
            }

            Transport transport = new Transport();
            transport.setCompany(company);
            transport.setClient(client);
            transport.setVehicle(vehicle);
            transport.setEmployee(employee);
            transport.setStartLocation(startLocation);
            transport.setEndLocation(endLocation);
            transport.setCargoDescription(cargoDescription);
            transport.setCargoWeight(cargoWeight);
            transport.setStartDate(startDate);
            transport.setEndDate(endDate);
            transport.setPrice(price);

            transportService.addTransport(transport);
            System.out.println("Transport added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding transport: " + e.getMessage());
        }
    }

    private static void viewAllTransports() {
        try {
            List<Transport> transports = transportService.getAllTransports();
            System.out.println("\nList of Transports:");
            for (Transport transport : transports) {
                System.out.printf("ID: %d, Company: %s, Client: %s, Vehicle: %s, Employee: %s, Start: %s, End: %s, Cargo: %s, Weight: %.2f, Price: %.2f\n",
                        transport.getTransportId(), transport.getCompany().getName(), transport.getClient().getName(),
                        transport.getVehicle().getModel(), transport.getEmployee().getName(), transport.getStartLocation(),
                        transport.getEndLocation(), transport.getCargoDescription(), transport.getCargoWeight(), transport.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving transports: " + e.getMessage());
        }
    }

    private static void viewTransportById() {
        System.out.print("Enter transport ID: ");
        int id = scanner.nextInt();
        try {
            Transport transport = transportService.getTransportById(id);
            if (transport != null) {
                System.out.printf("ID: %d, Company: %s, Client: %s, Vehicle: %s, Employee: %s, Start: %s, End: %s, Cargo: %s, Weight: %.2f, Price: %.2f\n",
                        transport.getTransportId(), transport.getCompany().getName(), transport.getClient().getName(),
                        transport.getVehicle().getModel(), transport.getEmployee().getName(), transport.getStartLocation(),
                        transport.getEndLocation(), transport.getCargoDescription(), transport.getCargoWeight(), transport.getPrice());
            } else {
                System.out.println("Transport not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving transport: " + e.getMessage());
        }
    }

    private static void updateTransport() {
        System.out.print("Enter transport ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            Transport transport = transportService.getTransportById(id);
            if (transport == null) {
                System.out.println("Transport not found.");
                return;
            }

            System.out.print("Enter new start location: ");
            String startLocation = scanner.nextLine();
            System.out.print("Enter new end location: ");
            String endLocation = scanner.nextLine();
            System.out.print("Enter new cargo description: ");
            String cargoDescription = scanner.nextLine();
            System.out.print("Enter new cargo weight: ");
            double cargoWeight = scanner.nextDouble();
            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            transport.setStartLocation(startLocation);
            transport.setEndLocation(endLocation);
            transport.setCargoDescription(cargoDescription);
            transport.setCargoWeight(cargoWeight);
            transport.setPrice(price);

            transportService.updateTransport(transport);
            System.out.println("Transport updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating transport: " + e.getMessage());
        }
    }

    private static void deleteTransport() {
        System.out.print("Enter transport ID: ");
        int id = scanner.nextInt();
        try {
            transportService.deleteTransport(id);
            System.out.println("Transport deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting transport: " + e.getMessage());
        }
    }

    private static void managePayments() {
        System.out.println("\nPayment Management");
        System.out.println("1. Add Payment");
        System.out.println("2. View All Payments");
        System.out.println("3. View Payment by ID");
        System.out.println("4. Update Payment");
        System.out.println("5. Delete Payment");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> addPayment();
            case 2 -> viewAllPayments();
            case 3 -> viewPaymentById();
            case 4 -> updatePayment();
            case 5 -> deletePayment();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addPayment() {
        System.out.print("Enter transport ID: ");
        int transportId = scanner.nextInt();
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter payment date (YYYY-MM-DD): ");
        Date paymentDate = java.sql.Date.valueOf(scanner.nextLine());
        System.out.print("Enter payment status (pending/completed): ");
        String status = scanner.nextLine();

        try {
            Transport transport = transportService.getTransportById(transportId);
            Client client = clientService.getClientById(clientId);

            if (transport == null || client == null) {
                System.out.println("Error: Related entities not found.");
                return;
            }

            Payment payment = new Payment();
            payment.setTransport(transport);
            payment.setClient(client);
            payment.setAmount(amount);
            payment.setPaymentDate(paymentDate);
            payment.setStatus(status);

            paymentService.addPayment(payment);
            System.out.println("Payment added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding payment: " + e.getMessage());
        }
    }

    private static void viewAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            System.out.println("\nList of Payments:");
            for (Payment payment : payments) {
                System.out.printf("ID: %d, Transport ID: %d, Client: %s, Amount: %.2f, Date: %s, Status: %s\n",
                        payment.getPaymentId(), payment.getTransport().getTransportId(),
                        payment.getClient().getName(), payment.getAmount(), payment.getPaymentDate(), payment.getStatus());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving payments: " + e.getMessage());
        }
    }

    private static void viewPaymentById() {
        System.out.print("Enter payment ID: ");
        int id = scanner.nextInt();
        try {
            Payment payment = paymentService.getPaymentById(id);
            if (payment != null) {
                System.out.printf("ID: %d, Transport ID: %d, Client: %s, Amount: %.2f, Date: %s, Status: %s\n",
                        payment.getPaymentId(), payment.getTransport().getTransportId(),
                        payment.getClient().getName(), payment.getAmount(), payment.getPaymentDate(), payment.getStatus());
            } else {
                System.out.println("Payment not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving payment: " + e.getMessage());
        }
    }

    private static void updatePayment() {
        System.out.print("Enter payment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
                System.out.println("Payment not found.");
                return;
            }

            System.out.print("Enter new amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            System.out.print("Enter new payment status (pending/completed): ");
            String status = scanner.nextLine();

            payment.setAmount(amount);
            payment.setStatus(status);

            paymentService.updatePayment(payment);
            System.out.println("Payment updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating payment: " + e.getMessage());
        }
    }

    private static void deletePayment() {
        System.out.print("Enter payment ID: ");
        int id = scanner.nextInt();
        try {
            paymentService.deletePayment(id);
            System.out.println("Payment deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting payment: " + e.getMessage());
        }
    }

    private static void dataExportImport() {
        System.out.println("\nData Export/Import");
        System.out.println("1. Export Transport Data to CSV");
        System.out.println("2. Import Transport Data from CSV");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try {
            switch (choice) {
                case 1 -> transportService.exportTransportsToFile();
                case 2 -> transportService.importTransportsFromFile();
                default -> System.out.println("Invalid option.");
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error with data export/import: " + e.getMessage());
        }
    }

    private static void generateReports() {
        System.out.println("\nReports");
        System.out.println("1. Total Transport Count");
        System.out.println("2. Total Transport Revenue");
        System.out.println("3. Transport Count by Driver");
        System.out.println("4. Revenue by Driver");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> displayTotalTransportCount();
            case 2 -> displayTotalTransportRevenue();
            case 3 -> displayTransportCountByDriver();
            case 4 -> displayRevenueByDriver();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void displayTotalTransportCount() {
        try {
            int totalTransports = transportService.getTotalTransportCount();
            System.out.println("Total Number of Transports: " + totalTransports);
        } catch (SQLException e) {
            System.out.println("Error fetching total transport count: " + e.getMessage());
        }
    }

    private static void displayTotalTransportRevenue() {
        try {
            double totalRevenue = transportService.getTotalTransportRevenue();
            System.out.println("Total Revenue from Transports: $" + totalRevenue);
        } catch (SQLException e) {
            System.out.println("Error fetching total transport revenue: " + e.getMessage());
        }
    }

    private static void displayTransportCountByDriver() {
        try {
            Map<String, Integer> driverTransportCount = transportService.getTransportCountByDriver();
            System.out.println("Transport Count by Driver:");
            for (Map.Entry<String, Integer> entry : driverTransportCount.entrySet()) {
                System.out.printf("Driver: %s, Transports: %d\n", entry.getKey(), entry.getValue());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transport count by driver: " + e.getMessage());
        }
    }

    private static void displayRevenueByDriver() {
        System.out.print("Enter driver ID to view revenue: ");
        int driverId = scanner.nextInt();
        try {
            double revenue = transportService.getRevenueByDriver(driverId);
            System.out.println("Total Revenue for Driver ID " + driverId + ": $" + revenue);
        } catch (SQLException e) {
            System.out.println("Error fetching revenue by driver: " + e.getMessage());
        }
    }

    private static void sortingAndFiltering() {
        System.out.println("\nSorting and Filtering");
        System.out.println("1. Sort Companies by Name");
        System.out.println("2. Sort Companies by Revenue");
        System.out.println("3. Sort Employees by Qualification");
        System.out.println("4. Sort Employees by Salary");
        System.out.println("5. Filter Transports by Destination");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> viewCompaniesSortedByName();
            case 2 -> viewCompaniesSortedByRevenue();
            case 3 -> viewEmployeesSortedByQualification();
            case 4 -> viewEmployeesSortedBySalary();
            case 5 -> filterTransportsByDestination();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void viewCompaniesSortedByName() {
        try {
            List<Company> companies = companyService.getCompaniesSortedByName();
            companies.forEach(company -> System.out.printf("ID: %d, Name: %s, Revenue: %.2f\n",
                    company.getCompanyId(), company.getName(), company.getRevenue()));
        } catch (SQLException e) {
            System.out.println("Error sorting companies by name: " + e.getMessage());
        }
    }

    private static void viewCompaniesSortedByRevenue() {
        try {
            List<Company> companies = companyService.getCompaniesSortedByRevenue();
            companies.forEach(company -> System.out.printf("ID: %d, Name: %s, Revenue: %.2f\n",
                    company.getCompanyId(), company.getName(), company.getRevenue()));
        } catch (SQLException e) {
            System.out.println("Error sorting companies by revenue: " + e.getMessage());
        }
    }

    private static void viewEmployeesSortedByQualification() {
        try {
            List<Employee> employees = employeeService.getEmployeesSortedByQualification();
            employees.forEach(employee -> System.out.printf("ID: %d, Name: %s, Qualification: %s, Salary: %.2f\n",
                    employee.getEmployeeId(), employee.getName(), employee.getQualification(), employee.getSalary()));
        } catch (SQLException e) {
            System.out.println("Error sorting employees by qualification: " + e.getMessage());
        }
    }

    private static void viewEmployeesSortedBySalary() {
        try {
            List<Employee> employees = employeeService.getEmployeesSortedBySalary();
            employees.forEach(employee -> System.out.printf("ID: %d, Name: %s, Qualification: %s, Salary: %.2f\n",
                    employee.getEmployeeId(), employee.getName(), employee.getQualification(), employee.getSalary()));
        } catch (SQLException e) {
            System.out.println("Error sorting employees by salary: " + e.getMessage());
        }
    }

    private static void filterTransportsByDestination() {
        System.out.print("Enter destination to filter transports: ");
        String destination = scanner.nextLine();
        try {
            List<Transport> transports = transportService.getTransportsByDestination(destination);
            transports.forEach(transport -> System.out.printf("ID: %d, Start: %s, End: %s, Cargo: %s, Price: %.2f\n",
                    transport.getTransportId(), transport.getStartLocation(), transport.getEndLocation(),
                    transport.getCargoDescription(), transport.getPrice()));
        } catch (SQLException e) {
            System.out.println("Error filtering transports by destination: " + e.getMessage());
        }
    }

}
