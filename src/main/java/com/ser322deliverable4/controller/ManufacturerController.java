package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Manufacturer;
import com.ser322deliverable4.service.manufacturer.IManuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ManufacturerController {

    private final IManuService manuService;

    private final Logger logger = LoggerFactory.getLogger(ManufacturerController.class);


    public ManufacturerController(IManuService manuService) {
        this.manuService = manuService;
    }

    @GetMapping("/manufacturer-services")
    public String addUserPage(Model model) {
        Manufacturer manufacturer = new Manufacturer();
        model.addAttribute("manufacturer", manufacturer);
        List<Manufacturer> manufacturerList = manuService.getAll();
        model.addAttribute("manList", manufacturerList);
        return "manufacturer-services";
    }

    @PostMapping("/add-manufacturer")
    public String addUser(@ModelAttribute("manufacturer") Manufacturer manufacturer, BindingResult bindingResult) {
        logger.info("RECEIVED MANUFAC: {}", manufacturer.getName());
        manuService.addManufacturer(manufacturer);
        logger.info("SAVED MANUFAC: {}", manufacturer.getCountry());
        return "redirect:manufacturer-services";
    }
}
