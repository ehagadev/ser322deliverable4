package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.model.Vehicle;
import com.ser322deliverable4.repository.FeatureRepository;
import com.ser322deliverable4.repository.ModelRepository;
import com.ser322deliverable4.repository.TrimLevelRepository;
import com.ser322deliverable4.service.vehicle.IVehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller()
public class VehicleController {

    private final IVehicleService vehicleService;

    private final FeatureRepository featureRepository;

    private final TrimLevelRepository trimLevelRepository;

    private final ModelRepository modelRepository;

    private final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    public VehicleController(IVehicleService vehicleService,
                             FeatureRepository featureRepository,
                             TrimLevelRepository trimLevelRepository,
                             ModelRepository modelRepository) {
        this.vehicleService = vehicleService;
        this.featureRepository = featureRepository;
        this.trimLevelRepository = trimLevelRepository;
        this.modelRepository = modelRepository;
    }

    @GetMapping(value = "/vehicle-services")
    public String getAllVehicles(Model model) {

        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        model.addAttribute("vehicleList", vehicleList);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    @GetMapping("/edit-vehicle/{vVin}")
    public String editUserPage(@PathVariable String vVin, Model model) {
        Vehicle vehicle = vehicleService.getVehicleByVin(vVin);
        model.addAttribute("vehicle", vehicle);
        List<com.ser322deliverable4.model.Model> allModels = modelRepository.findAll();
        allModels.spliterator().forEachRemaining(
                m -> logger.info("RETRIEVED MODEL: {}", m.getName())
        );

        model.addAttribute("allModels", allModels);
        return "edit-vehicle";
    }

    @PostMapping("/edit-vehicle")
    public String editVehicle(@RequestParam String modelName, @ModelAttribute("editVehicle") Vehicle vehicle, BindingResult bindingResult) {
        logger.info("EDIT VEHICLE VIN: {}", vehicle.getVin());
        logger.info("EDIT VEHICLE MODEL NAME: {}", modelName);
        vehicleService.editVehicle(vehicle, modelName);
        return "redirect:vehicle-services";
    }

    @GetMapping("/delete-vehicle/{vVin}")
    public String deleteVehicle(@PathVariable String vVin) {
        vehicleService.deleteVehicleByVin(vVin);
        return "redirect:../vehicle-services";
    }

    @GetMapping("/vehicle-by-vin")
    public String searchByVin(@RequestParam String vin, Model model) {
        Vehicle vehicleByVin = vehicleService.getVehicleByVin(vin);

        List<Vehicle> vehicleList = List.of(vehicleByVin);
        model.addAttribute("vehicleList", vehicleList);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    @GetMapping("/vehicles-by-feature")
    public String searchByFeature(@RequestParam String featureName, Model model) {
        List<Vehicle> vehiclesByFeatureName = vehicleService.getVehicleByFeatureName(featureName);
        model.addAttribute("vehicleList", vehiclesByFeatureName);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    @GetMapping("/vehicles-by-trim-level")
    public String searchByTrimLevel(@RequestParam String trimLevelName, Model model) {
        List<Vehicle> vehiclesByTrimLevelName = vehicleService.getVehiclesByTimeLevel(trimLevelName);
        model.addAttribute("vehicleList", vehiclesByTrimLevelName);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    /**
     * Implement and Streamline this later
     * @param model
     */
//    private void reloadBackgroundData(Model model) {
//
//        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
//        model.addAttribute("vehicleList", vehicleList);
//
//        List<Feature> allFeatures = featureRepository.findAll();
//        model.addAttribute("allFeatures", allFeatures);
//    }
}
