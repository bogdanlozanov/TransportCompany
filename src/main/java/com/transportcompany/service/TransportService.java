package com.transportcompany.service;

import com.transportcompany.model.*;
import com.transportcompany.repository.TransportRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;

public class TransportService {
    // Define default file paths
    private static final String EXPORT_FILE_PATH = "data/transport_data_export.csv";
    private static final String IMPORT_FILE_PATH = "data/transport_data_import.csv";

    private final TransportRepository transportRepository;

    public TransportService() {
        this.transportRepository = new TransportRepository();
    }

    public void addTransport(Transport transport) throws SQLException {
        if (transport.getStartLocation() == null || transport.getStartLocation().isEmpty()) {
            throw new IllegalArgumentException("Start location cannot be null or empty");
        }
        transportRepository.addTransport(transport);
    }

    public Transport getTransportById(int id) throws SQLException {
        return transportRepository.getTransportById(id);
    }

    public List<Transport> getAllTransports() throws SQLException {
        return transportRepository.getAllTransports();
    }

    public void updateTransport(Transport transport) throws SQLException {
        if (transport.getStartLocation() == null || transport.getStartLocation().isEmpty()) {
            throw new IllegalArgumentException("Start location cannot be null or empty");
        }
        transportRepository.updateTransport(transport);
    }

    public void deleteTransport(int transportId) throws SQLException {
        transportRepository.deleteTransport(transportId);
    }

    public void exportTransportsToFile() throws SQLException, IOException {
        List<Transport> transports = transportRepository.getAllTransports();

        // Create the 'data' directory if it doesn't exist
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPORT_FILE_PATH))) {
            writer.write("ID,Company,Client,Vehicle,Employee,Start Location,End Location,Cargo Description,Cargo Weight,Start Date,End Date,Price\n");
            for (Transport transport : transports) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%.2f,%s,%s,%.2f\n",
                        transport.getTransportId(),
                        transport.getCompany().getName(),
                        transport.getClient().getName(),
                        transport.getVehicle().getModel(),
                        transport.getEmployee().getName(),
                        transport.getStartLocation(),
                        transport.getEndLocation(),
                        transport.getCargoDescription(),
                        transport.getCargoWeight(),
                        transport.getStartDate(),
                        transport.getEndDate(),
                        transport.getPrice()));
            }
            System.out.println("Transport data exported to " + EXPORT_FILE_PATH);
        }
    }

    public void importTransportsFromFile() throws SQLException, IOException {
        File importFile = new File(IMPORT_FILE_PATH);
        if (!importFile.exists()) {
            System.out.println("Import file not found at " + IMPORT_FILE_PATH);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(IMPORT_FILE_PATH))) {
            String line = reader.readLine();  // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Transport transport = new Transport();
                transport.setCompany(new Company(Integer.parseInt(fields[1])));
                transport.setClient(new Client(Integer.parseInt(fields[2])));
                transport.setVehicle(new Vehicle(Integer.parseInt(fields[3])));
                transport.setEmployee(new Employee(Integer.parseInt(fields[4])));
                transport.setStartLocation(fields[5]);
                transport.setEndLocation(fields[6]);
                transport.setCargoDescription(fields[7]);
                transport.setCargoWeight(Double.parseDouble(fields[8]));
                transport.setStartDate(java.sql.Date.valueOf(fields[9]));
                transport.setEndDate(java.sql.Date.valueOf(fields[10]));
                transport.setPrice(Double.parseDouble(fields[11]));

                transportRepository.addTransport(transport);
            }
            System.out.println("Transport data imported from " + IMPORT_FILE_PATH);
        }
    }

    public int getTotalTransportCount() throws SQLException {
        return transportRepository.getAllTransports().size();
    }

    public double getTotalTransportRevenue() throws SQLException {
        double totalRevenue = 0;
        for (Transport transport : transportRepository.getAllTransports()) {
            totalRevenue += transport.getPrice();
        }
        return totalRevenue;
    }

    public Map<String, Integer> getTransportCountByDriver() throws SQLException {
        Map<String, Integer> driverTransportCount = new HashMap<>();
        List<Transport> transports = transportRepository.getAllTransports();
        for (Transport transport : transports) {
            String driverName = transport.getEmployee().getName();
            driverTransportCount.put(driverName, driverTransportCount.getOrDefault(driverName, 0) + 1);
        }
        return driverTransportCount;
    }

    public double getRevenueByDriver(int driverId) throws SQLException {
        double revenue = 0;
        List<Transport> transports = transportRepository.getAllTransports();
        for (Transport transport : transports) {
            if (transport.getEmployee().getEmployeeId() == driverId) {
                revenue += transport.getPrice();
            }
        }
        return revenue;
    }

    public List<Transport> getTransportsByDestination(String destination) throws SQLException {
        return transportRepository.getTransportsByDestination(destination);
    }
}
