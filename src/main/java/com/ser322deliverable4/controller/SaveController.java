package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Saves;
import com.ser322deliverable4.model.User;
import com.ser322deliverable4.model.Vehicle;
import com.ser322deliverable4.service.save.ISaveService;
import com.ser322deliverable4.service.user.IUserService;
import com.ser322deliverable4.service.vehicle.IVehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class SaveController {

    //private final IVehicleService vehicleService;

    private final IUserService userService;

    private final ISaveService savesService;
	
	private final IVehicleService vehicleService;

    private final Logger logger = LoggerFactory.getLogger(SaveController.class);

    public SaveController(ISaveService savesService, IUserService userService, IVehicleService vehicleService) {
        this.savesService = savesService;
		this.userService = userService;
		this.vehicleService = vehicleService;
    }

    @GetMapping("/saves-services")
    public String getAllSaves(Model model) {
        //List<User> userList = userService.getAllUsers();
        //model.addAttribute("userList", userList);

        //List<Vehicle> vehicleList = vehicleSerice.getAllVehicles();
        //model.addAtrribute("vehicleList", vehicleList);
		
        Saves save = new Saves();
        model.addAttribute("save", save);
		
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);

        List<Saves> savesList = savesService.getAllSaves();
        model.addAttribute("savesList", savesList);
		
        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        model.addAttribute("vehicleList", vehicleList);

        return "saves-services";
    }
	
    @PostMapping("/save-new-vehicle-for-user")
    public String saveNewVehicleForUser(@ModelAttribute("save") Saves save, BindingResult bindingResult) {
		//User user = userService.getUserById();
		//Vehicle vehice = vehicleService.getVehicleByVin();
		//Save
		//savesService.addSave(save);
        //logger.info("RECEIVED USER: {}", user.getEmail());
        //userService.addUser(user);
        //logger.info("SAVED USER: {}", user.getEmail());
        return "redirect:saves-services";
    }



/*
    @PostMapping("/saveNewVehicleForUserr")
    public String saveTrimLevel(@ModelAttribute("trimLevel") TrimLevel trimLevel, BindingResult bindingResult) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findByName(trimLevel.getManufacturer().getName());
        if (optionalManufacturer.isPresent()) {
            Manufacturer manufacturer = optionalManufacturer.get();
            logger.info("Received manufacturer name: {}", manufacturer.getName());
            trimLevel.setManufacturer(manufacturer);
            trimLevelService.addTrimLevel(trimLevel);
        } else {
            logger.error("No Manufacturer found with the given name: {}", trimLevel.getManufacturer().getName());
        }
        return "redirect:trim-level-services";
    }
*/

}
