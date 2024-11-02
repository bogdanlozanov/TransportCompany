package com.transportcompany.service;

import com.transportcompany.model.Vehicle;
import com.transportcompany.repository.VehicleRepository;

import java.sql.SQLException;
import java.util.List;

public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService() {
        this.vehicleRepository = new VehicleRepository();
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        if (vehicle.getVehicleType() == null || vehicle.getVehicleType().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }
        vehicleRepository.addVehicle(vehicle);
    }

    public Vehicle getVehicleById(int id) throws SQLException {
        return vehicleRepository.getVehicleById(id);
    }

    public List<Vehicle> getAllVehicles() throws SQLException {
        return vehicleRepository.getAllVehicles();
    }

    public void updateVehicle(Vehicle vehicle) throws SQLException {
        if (vehicle.getVehicleType() == null || vehicle.getVehicleType().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }
        vehicleRepository.updateVehicle(vehicle);
    }

    public void deleteVehicle(int vehicleId) throws SQLException {
        vehicleRepository.deleteVehicle(vehicleId);
    }
}
