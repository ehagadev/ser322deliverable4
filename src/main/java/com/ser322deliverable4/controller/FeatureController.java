package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.service.feature.IFeatureService;
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
public class FeatureController {
    private final IFeatureService featureService;

    private final Logger logger = LoggerFactory.getLogger(FeatureController.class);

    public FeatureController(IFeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping("/feature-services")
    public String addFeaturePage(Model model) {
        Feature feature = new Feature();
        model.addAttribute("feature", feature);
        List<Feature> featureList = featureService.getAll();
        model.addAttribute("featureList", featureList);
        logger.info("RENDERING ADD FEATURE PAGE");
        return "feature-services";
    }

    @PostMapping("/add-feature")
    public String addFeature(@ModelAttribute("feature") Feature feature, BindingResult bindingResult) {
        logger.info("RECEIVED FEATURE: " + feature.getFeatureName());
        featureService.addFeature(feature);
        logger.info("SAVED FEATURE: " + feature.getFeatureName());
        return "redirect:feature-services";
    }

    @GetMapping("/edit-feature/{featureName}")
    public String editFeaturePage(@PathVariable String featureName, Model model) {
        Feature feature = featureService.getFeatureByName(featureName);
        model.addAttribute("editFeature", feature);
        return "edit-feature";
    }

    @PostMapping("/edit-feature")
    public String editFeature(@ModelAttribute("editFeature") Feature editFeature, BindingResult bindingResult) {
        logger.info("EDIT FEATURE NAME: " + editFeature.getFeatureName());
        logger.info("EDIT FEATURE DESCRIPTION: " + editFeature.getDescription());
        int response = featureService.editFeature(editFeature);
        logger.info("ROWS CHANGED IN DB: " + response);
        return "redirect:feature-services";
    }

    @GetMapping("/delete-feature/{featureName}")
    public String deleteFeature(@PathVariable String featureName, RedirectAttributes redirectAttributes) {
        logger.info("DELETING FEATURE: " + featureName);
        try {
            int response = featureService.deleteFeature(featureName);
            logger.info("ROWS CHANGED IN DB: {}", response);
            return "redirect:../feature-services";
        } catch (SQLIntegrityConstraintViolationException ex) {
            logger.error("SQL INTEGRITY CONSTRAINT VIOLATION EXCEPTION");
            redirectAttributes.addFlashAttribute("error", "Cannot delete manufacturer due to foreign key constraint.");
            return "redirect:/feature-services";
        }
    }
}
