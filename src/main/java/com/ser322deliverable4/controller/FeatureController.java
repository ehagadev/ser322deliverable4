package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.service.feature.IFeatureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class FeatureController {
    private final IFeatureService featureService;

    private final Logger logger = Logger.getLogger(FeatureController.class.getName());

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


}
