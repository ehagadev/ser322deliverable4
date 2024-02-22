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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
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

    @PostMapping("/edit-manufacturer")
    public String editManufacturer(@ModelAttribute("editManufacturer") Manufacturer editManufacturer, BindingResult bindingResult) {
        logger.info("EDIT MANUFACTURER NAME: {}", editManufacturer.getName());
        logger.info("EDIT MANUFACTURER COUNTRY: {}", editManufacturer.getCountry());
        int response = manuService.editManufacturer(editManufacturer);
        logger.info("ROWS CHANGED IN DB: {}", response);
        return "redirect:manufacturer-services";
    }

    @GetMapping("/edit-manufacturer/{manName}")
    public String editManufacturerPage(@PathVariable String manName, Model model) {
        Manufacturer manufacturer = manuService.getByName(manName);
        model.addAttribute("editManufacturer", manufacturer);
        return "edit-manufacturer";
    }

    @GetMapping("/delete-manufacturer/{manName}")
    public String deleteManufacturer(@PathVariable String manName, RedirectAttributes redirectAttributes) {
        logger.info("DELETE MANUFACTURER BY NAME: {}", manName);
        try {
            int response = manuService.deleteManufacturer(manName);
            logger.info("ROWS CHANGED IN DB: {}", response);
            return "redirect:../manufacturer-services";
        } catch (SQLIntegrityConstraintViolationException ex) {
            logger.error("SQL INTEGRITY CONSTRAINT VIOLATION EXCEPTION");
            redirectAttributes.addFlashAttribute("error", "Cannot delete manufacturer due to foreign key constraint.");
            return "redirect:/manufacturer-services";
        }

    }
}
