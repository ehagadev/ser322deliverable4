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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

        List<com.ser322deliverable4.model.Model> allModels = modelRepository.findAll();
        model.addAttribute("allModels", allModels);

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

    @PostMapping("/add-vehicle")
    public String addVehicle(@ModelAttribute Vehicle vehicle, @RequestParam Long modelId) {
        Optional<com.ser322deliverable4.model.Model> modelOptional = modelRepository.findById(modelId);

        if (modelOptional.isPresent()) {
            vehicle.setModel(modelOptional.get());

            vehicleService.addVehicle(vehicle);
        } else {
            logger.error("Model with id {} not found", modelId);
        }

        return "redirect:/vehicle-services";
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
     * Search for Vehicles based on a provided year
     * @param year
     * @param model
     * @return
     */
    @GetMapping("/vehicles-by-year")
    public String searchByYear(@RequestParam String year, Model model) {
        List<Vehicle> vehiclesByYear = vehicleService.getVehiclesByYear(year);
        model.addAttribute("vehicleList", vehiclesByYear);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    /**
     * Search for Vehicles based on a provided year
     * @param modelName
     * @param model
     * @return
     */
    @GetMapping("/vehicles-by-ModelName")
    public String searchByModelName(@RequestParam String modelName, Model model) {
        List<Vehicle> vehiclesByModelName = vehicleService.getVehiclesByModelName(modelName);
        model.addAttribute("vehicleList", vehiclesByModelName);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    /**
     * Search for Vehicles by manufacturer
     * @param mfg
     * @param model
     * @return
     */
    @GetMapping("/vehicles-by-mfg")
    public String searchByMfg(@RequestParam String mfg, Model model) {
        List<Vehicle> vehiclesByMfg = vehicleService.getVehiclesByMfg(mfg);
        model.addAttribute("vehicleList", vehiclesByMfg);

        List<Feature> allFeatures = featureRepository.findAll();
        model.addAttribute("allFeatures", allFeatures);

        List<TrimLevel> allTrimLevels = trimLevelRepository.findAll();
        model.addAttribute("allTrimLevels", allTrimLevels);

        return "vehicle-services";
    }

    /**
     * Search for Vehicles by manufacturer, trim level and color
     * @param mfg
     * @param trimLevel
     * @param color
     * @param model
     * @return
     */
    @GetMapping("/vehicles-by-mtc")
    public String searchByMtc(@RequestParam String mfg, @RequestParam String trimLevel, @RequestParam String color, Model model) {
        List<Vehicle> vehiclesByMtc = vehicleService.getVehiclesByMtc(mfg, trimLevel, color);
        model.addAttribute("vehicleList", vehiclesByMtc);

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
