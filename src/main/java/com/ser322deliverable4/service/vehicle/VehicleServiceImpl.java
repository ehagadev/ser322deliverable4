package com.ser322deliverable4.service.vehicle;

import com.ser322deliverable4.model.Model;
import com.ser322deliverable4.model.Vehicle;
import com.ser322deliverable4.repository.ModelRepository;
import com.ser322deliverable4.repository.SavesRepository;
import com.ser322deliverable4.repository.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  VehicleServiceImpl implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;
    private final SavesRepository savesRepository;
    private final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              ModelRepository modelRepository,
                              SavesRepository savesRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelRepository = modelRepository;
        this.savesRepository = savesRepository;
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
    
    @Override
    public List<Vehicle> getVehiclesByYear(String year) {
        logger.info("ATTEMPTING TO FIND VEHICLES BY YEAR: {}", year);
        List<Vehicle> vehiclesByYear = vehicleRepository.findVehiclesByYear(year);
        logger.info("FOUND VEHICLES BY YEAR: {}", year);
        return vehiclesByYear;
    }

    @Override
    public List<Vehicle> getVehiclesByModelName(String modelName) {
        logger.info("ATTEMPTING TO FIND VEHICLES BY MODEL NAME: {}", modelName);
        List<Vehicle> vehiclesByModelName = vehicleRepository.findVehiclesByModelName(modelName);
        logger.info("FOUND VEHICLES BY MODEL NAME: {}", modelName);
        return vehiclesByModelName;
    }
    
    @Override
    public List<Vehicle> getVehiclesByMfg(String mfg) {
        logger.info("ATTEMPTING TO FIND VEHICLES BY MANUFACTURER: {}", mfg);
        List<Vehicle> vehiclesByMfg = vehicleRepository.findVehiclesByMfg(mfg);
        logger.info("FOUND VEHICLES BY Manufacturer: {}", mfg);
        return vehiclesByMfg;
    }

    @Override
    public List<Vehicle> getVehiclesByMtc(String mfg, String trimLevel, String color) {
        logger.info("ATTEMPTING TO FIND VEHICLES BY MANUFACTURER TRIM AND COLOR: {} {} {}", mfg, trimLevel, color);
        List<Vehicle> vehiclesByMtc = vehicleRepository.findVehiclesByMtc(mfg, trimLevel, color);
        logger.info("FOUND VEHICLES BY MANUFACTURER TRIM AND COLOR: {} {} {}", mfg, trimLevel, color);
        return vehiclesByMtc;
    }

    @Override
    public void editVehicle(Vehicle vehicle, String modelName) {
        Vehicle foundByVin = vehicleRepository.findVehicleByVin(vehicle.getVin())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with VIN: " + vehicle.getVin()));
        Model model = modelRepository.findModelByName(modelName)
                .orElseThrow(() -> new EntityNotFoundException("Model not found with name: " + modelName));
        foundByVin.setModel(model);
        foundByVin.setColor(vehicle.getColor());
        vehicleRepository.saveCustom(foundByVin.getVin(), foundByVin.getColor(), foundByVin.getModel().getModelId());
        logger.info("EDITED VEHICLE NOW HAS ASSOCIATED MODEL: {}", foundByVin.getModel().getName());

    }

    @Override
    public String deleteVehicleByVin(String vVin) {
        String response;
        Vehicle foundByVin = vehicleRepository.findVehicleByVin(vVin)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with VIN: " + vVin));
        savesRepository.findSavesByVehicleVin(vVin).forEach(saves -> {
            logger.info("Deleting Saves - User ID: {}, Vehicle VIN: {}", saves.getUser().getId(), saves.getVehicle().getVin());
            savesRepository.delete(saves);
        });
        try {
            vehicleRepository.deleteByVin(foundByVin.getVin());
            response = "GOOD";
        } catch (Exception e) {
            logger.info("UNABLE TO DELETE VEHICLE, FK CONSTRAINTS: {}", foundByVin);
            response = "BAD";
        }
        return response;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        logger.info("SUCCESSFULLY ADDED NEW VEHICLE {} INTO THE DB", vehicle);
        return vehicle;
    }

}
