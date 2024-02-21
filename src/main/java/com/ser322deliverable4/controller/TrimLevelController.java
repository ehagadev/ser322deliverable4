package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Manufacturer;
import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.repository.ManufacturerRepository;
import com.ser322deliverable4.repository.TrimLevelRepository;
import com.ser322deliverable4.service.trimlevel.ITrimLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrimLevelController {

    @Autowired
    private ITrimLevelService trimLevelService;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

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

    @PostMapping("/saveTrimLevel")
    public String saveTrimLevel(@ModelAttribute("trimLevel") TrimLevel trimLevel,
                                BindingResult bindingResult,
                                @RequestParam String manufacturerName) {

        if (bindingResult.hasErrors()) {
            return "trim-level-form";
        }
        Logger logger = LoggerFactory.getLogger(TrimLevelController.class);
        logger.info("Received manufacturer name: {}", manufacturerName);

        Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName);
        if (manufacturer == null) {
            System.out.println("Manufacturer not found");
            return "trim-level-form";
        }
        trimLevel.setManufacturer(manufacturer);
        trimLevelService.addTrimLevel(trimLevel);

        return "redirect:trim-level-services";
    }

}
