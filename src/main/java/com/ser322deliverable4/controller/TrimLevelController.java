package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Manufacturer;
import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.repository.ManufacturerRepository;
import com.ser322deliverable4.service.trimlevel.ITrimLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TrimLevelController {

    @Autowired
    private ITrimLevelService trimLevelService;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    private final Logger logger = LoggerFactory.getLogger(TrimLevelController.class.getName());

    @GetMapping("/trim-level-services")
    public String addTrimLevelPage(Model model) {
        TrimLevel trimLevel = new TrimLevel();
        model.addAttribute("trimLevel", trimLevel);
        List<TrimLevel> trimLevelList = trimLevelService.getAllTrimLevels();
        model.addAttribute("trimLevelList", trimLevelList);
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "trim-level-services";
    }

    @GetMapping("/addTrimLevel")
    public String showForm(Model model) {
        return "trim-level-form";
    }

    @GetMapping("/edit-trim-level/{trimId}")
    public String editTrimLevelPage(@PathVariable Long trimId, Model model) {
        logger.info("EDIT TRIM LEVEL ID: " + trimId);
        TrimLevel trimLevel = trimLevelService.getTrimLevelById(trimId);
        logger.info("EDIT TRIM LEVEL NAME: " + trimLevel.getName());
        model.addAttribute("editTrimLevel", trimLevel);
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);
        logger.info("Leaving edit-trim-level/{trimId}");
        return "edit-trim-level";
    }

    @PostMapping("/edit-trim-level")
    public String editTrimLevel(@ModelAttribute("editTrimLevel") TrimLevel trimLevel, BindingResult bindingResult) {
        logger.info("EDIT TRIM LEVEL NAME: " + trimLevel.getName());
        logger.info("EDIT TRIM LEVEL YEAR: " + trimLevel.getYear());
        Manufacturer manufacturer = manufacturerRepository.findByName(trimLevel.getManufacturer().getName());
        if (manufacturer == null) {
            logger.info("Manufacturer not found");
            return "edit-trim-level";
        }
        logger.info("Received manufacturer name: {}", manufacturer.getName());
        trimLevel.setManufacturer(manufacturer);
        int response = trimLevelService.editTrimLevel(trimLevel);
        logger.info("ROWS CHANGED IN DB: " + response);
        return "redirect:trim-level-services";
    }

    @PostMapping("/saveTrimLevel")
    public String saveTrimLevel(@ModelAttribute("trimLevel") TrimLevel trimLevel, BindingResult bindingResult) {
        Logger logger = LoggerFactory.getLogger(TrimLevelController.class);
        Manufacturer manufacturer = manufacturerRepository.findByName(trimLevel.getManufacturer().getName());
        logger.info("Received manufacturer name: {}", manufacturer.getName());
        trimLevel.setManufacturer(manufacturer);
        trimLevelService.addTrimLevel(trimLevel);
        return "redirect:trim-level-services";
    }

    @GetMapping("/delete-trim-level/{trimId}")
    public String deleteTrimLevel(@PathVariable Long trimId) {
        TrimLevel trimLevel = trimLevelService.getTrimLevelById(trimId);
        if (trimLevel == null) {
            logger.info("Trim level not found");
            return "redirect:/trim-level-services";
        }
        trimLevelService.deleteTrimLevel(trimId);
        return "redirect:/trim-level-services";
    }
}
