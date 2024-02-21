package com.ser322deliverable4.service.vehicle;

import com.ser322deliverable4.model.Vehicle;
import com.ser322deliverable4.repository.VehicleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  VehicleServiceImpl implements IVehicleService {

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

    @Override
    public List<Vehicle> getVehicleByFeatureName(String featureName) {
        logger.info("ATTEMPTING TO FIND VEHICLES BY FEATURE NAME: {}", featureName);
        List<Vehicle> vehicleByFeatureName = vehicleRepository.findVehicleByFeatureName(featureName);
        logger.info("FOUND VEHICLES BY FEATURE NAME: {}", vehicleByFeatureName);
        return vehicleByFeatureName;
    }

    @Override
    public List<Vehicle> getVehiclesByTimeLevel(String trimLevelName) {
        logger.info("ATTEMPTING TO FIND VEHICLES BY TRIM LEVEL: {}", trimLevelName);
        List<Vehicle> vehiclesByTrimLevelName = vehicleRepository.findVehiclesByTrimLevelName(trimLevelName);
        logger.info("FOUND VEHICLES BY TRIM LEVEL NAME: {}", vehiclesByTrimLevelName);
        return vehiclesByTrimLevelName;
    }

}
