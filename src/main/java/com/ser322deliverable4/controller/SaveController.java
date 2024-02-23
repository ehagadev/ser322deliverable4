package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Saves;
import com.ser322deliverable4.model.User;
import com.ser322deliverable4.model.Vehicle;
import com.ser322deliverable4.service.saves.ISaveService;
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

import com.ser322deliverable4.repository.UserRepository;
import com.ser322deliverable4.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Controller
public class SaveController {

    private final IUserService userService;

    private final ISaveService savesService;
	
	private final IVehicleService vehicleService;
	
    @Autowired
    private UserRepository userRepository;
	
    @Autowired
    private VehicleRepository vehicleRepository;
	

    private final Logger logger = LoggerFactory.getLogger(SaveController.class);

    public SaveController(ISaveService savesService, IUserService userService, IVehicleService vehicleService) {
        this.savesService = savesService;
		this.userService = userService;
		this.vehicleService = vehicleService;
    }

    @GetMapping("/saves-services")
    public String getAllSaves(Model model) {
        Saves save = new Saves();
        model.addAttribute("save", save);
		
        User user = new User();
        model.addAttribute("user", user);
		
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
		
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);

        List<Saves> savesList = savesService.getAllSaves();
        model.addAttribute("savesList", savesList);
		
        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        model.addAttribute("vehicleList", vehicleList);

        return "saves-services";
    }
	
    @PostMapping("/save-new-vehicle-for-user")
    public String saveNewVehicleForUser(@ModelAttribute("user") User user, 
										@ModelAttribute("vehicle") Vehicle vehicle, 
										BindingResult bindingResult) {

        logger.info("RECEIVED USER: {}", user.getEmail());
        logger.info("RECEIVED VEHICLE: {}", vehicle);

		Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
		User user1 = user;
		
		Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByVin(vehicle.getVin());
		Vehicle vehicle1 = vehicle;
		
        if (optionalUser.isPresent()) {
            user1 = optionalUser.get();
            logger.info("Received user userid: {}", user1.getId());
        } else {
            logger.error("No user found with the given id: {}", user1.getId());
        }

        if (optionalVehicle.isPresent()) {
            vehicle1 = optionalVehicle.get();
            logger.info("Received vehicle vin: {}", vehicle1.getVin());
        } else {
            logger.error("No vehicle found with the given vin: {}", vehicle1.getVin());
        }

		Saves newSave = new Saves(user1, vehicle1);
		savesService.addSave(newSave);
		
		return "redirect:saves-services";
    }

}


