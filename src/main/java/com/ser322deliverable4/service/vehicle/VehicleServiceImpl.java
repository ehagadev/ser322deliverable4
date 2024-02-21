package com.ser322deliverable4.service.vehicle;

import com.ser322deliverable4.model.User;
//import com.ser322deliverable4.model.User;
import com.ser322deliverable4.model.Vehicle;
//import com.ser322deliverable4.repository.UserRepository;
import com.ser322deliverable4.repository.VehicleRepository;
//import com.ser322deliverable4.service.user.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl implements IVehicleService {

    private final VehicleRepository vehicleRepository;

    private final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        logger.info("FETCHING ALL VEHICLES");
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        logger.info("ATTEMPTING TO FIND VEHICLE BY VIN: {}", vin);
        Optional<Vehicle> byVin = vehicleRepository.findVehicleByVin(vin);

        if (byVin.isPresent()) {
            Vehicle vehicle = byVin.get();
            logger.info("FOUND VEHICLE BY VIN: {}", vehicle.getVin());
            return vehicle;
        } else {
            logger.error("VEHICLE NOT FOUND BY VIN: {}", vin);
            return null;
        }
    }
    
}
