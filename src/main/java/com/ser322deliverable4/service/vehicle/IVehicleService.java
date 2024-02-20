package com.ser322deliverable4.service.vehicle;

import com.ser322deliverable4.model.User;
import com.ser322deliverable4.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    
    List<Vehicle> getAllVehicles();

    Vehicle getVehicleByVin(String vin);

}
