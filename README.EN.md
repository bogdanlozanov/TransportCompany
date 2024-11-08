# Transportation Company - Application for Managing Transport Activities

## Application Description

This application is designed to manage the operations of a transportation company. It provides functionalities for managing transport companies, clients, vehicles, employees, transport records, and payments. The application uses a relational database (MySQL) and is written in Java.

## Application Features

### 1. Company Management
- **Add New Company** - Allows the user to enter the company name and revenue.
- **View All Companies** - Displays a list of all registered companies.
- **View Company by ID** - Enables viewing information for a specific company by its ID.
- **Edit Company** - Allows updating the information of a selected company.
- **Delete Company** - Allows deleting a company from the database.

### 2. Client Management
- **Add New Client** - Allows the user to add a client associated with a specific company.
- **View All Clients** - Displays a list of all clients of the transportation company.
- **View Client by ID** - Enables viewing information for a specific client by their ID.
- **Edit Client** - Allows updating client information.
- **Delete Client** - Deletes a client from the database.

### 3. Vehicle Management
- **Add New Vehicle** - Allows the user to add a vehicle to a company (e.g., truck or bus).
- **View All Vehicles** - Displays a list of all vehicles in the database.
- **View Vehicle by ID** - Enables viewing information for a specific vehicle by its ID.
- **Edit Vehicle** - Allows updating vehicle information.
- **Delete Vehicle** - Removes a vehicle from the database.

### 4. Employee Management
- **Add New Employee** - Allows the user to add an employee to a company and specify their qualification and salary.
- **View All Employees** - Displays a list of all employees.
- **View Employee by ID** - Enables viewing information for a specific employee by their ID.
- **Edit Employee** - Allows updating employee information.
- **Delete Employee** - Removes an employee from the database.

### 5. Transport Management
- **Add New Transport** - Allows the user to add a new transport with details such as start and end locations, cargo, dates, and price.
- **View All Transports** - Displays a list of all transport records.
- **View Transport by ID** - Enables viewing information for a specific transport record.
- **Edit Transport Record** - Allows updating details of a transport.
- **Delete Transport Record** - Removes a transport record from the database.

### 6. Payment Management
- **Add New Payment** - Allows the user to register a payment associated with a specific transport and client.
- **View All Payments** - Displays a list of all payments.
- **View Payment by ID** - Enables viewing information for a specific payment.
- **Edit Payment** - Allows updating the amount and status of a payment (e.g., "completed" or "pending").
- **Delete Payment** - Deletes a payment from the database.

### 7. Import and Export of Transport Data
- **Export Transport Data** - Exports all transport records to a CSV file, which is automatically saved in the `data` folder of the project as `transport_data_export.csv`.
- **Import Transport Data** - Imports transport records from a CSV file (`transport_data_import.csv`), which should be placed in the `data` folder.

### 8. Reports
- **Total Transport Count** - Displays the total number of transport records.
- **Total Revenue from Transports** - Calculates and displays the total revenue from all transports.
- **Transport Count by Driver** - Shows the number of transports completed by each driver.
- **Revenue by Driver** - Displays the revenue generated by each driver.

### 9. Sorting and Filtering Data
- **Sort Companies** - Sorts companies by name or revenue.
- **Sort Employees** - Sorts employees by qualification or salary.
- **Filter Transports by Destination** - Allows filtering transport records by the end location.


