package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Vehicle;
import com.ser322deliverable4.service.vehicle.IVehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller()
public class VehicleController {

    private final IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/vehicle-services")
    public String getAllVehicles(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        model.addAttribute("vehicleList", vehicleList);
        return "vehicle-services";
    }

    @GetMapping("/vehicle-by-vin")
    public String searchByVin(@RequestParam String vin, Model model) {
        Vehicle vehicleByVin = vehicleService.getVehicleByVin(vin);
        List<Vehicle> vehicleList = List.of(vehicleByVin);
        model.addAttribute("vehicleList", vehicleList);
        return "vehicle-services";
    }

}
