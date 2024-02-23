package com.ser322deliverable4.controller;

import com.ser322deliverable4.composites.TrimFeatureId;
import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.model.TrimFeatures;
import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.repository.FeatureRepository;
import com.ser322deliverable4.repository.TrimFeaturesRepository;
import com.ser322deliverable4.repository.TrimLevelRepository;
import com.ser322deliverable4.service.trimfeature.ITrimFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TrimFeatureController {

    @Autowired
    private TrimLevelRepository trimLevelRepository;

    @Autowired
    private FeatureRepository featureRepository;

    private final ITrimFeatureService trimFeatureService;

    @Autowired
    private TrimFeaturesRepository trimFeaturesRepository;

    private final Logger logger = LoggerFactory.getLogger(TrimFeatureController.class);

    public TrimFeatureController(ITrimFeatureService trimFeatureService) {
        this.trimFeatureService = trimFeatureService;
    }

    @GetMapping("/trim-feature-services")
    public String addTrimFeaturePage(Model model) {
        TrimFeatures trimFeatures = new TrimFeatures();
        model.addAttribute("trimFeatures", trimFeatures);

        List<TrimFeatures> trimFeaturesList = trimFeatureService.getAllTrimFeatures();
        model.addAttribute("trimFeaturesList", trimFeaturesList);

        List<TrimLevel> trimLevels = trimLevelRepository.findAll();
        model.addAttribute("trimLevels", trimLevels);

        List<Feature> features = featureRepository.findAll();
        model.addAttribute("features", features);

        return "trim-feature-services";
    }

    @PostMapping("/add-trim-feature")
    public String addTrimFeature(@RequestParam("trimLevelId") Long trimLevelId, @RequestParam("featureName") String featureName, Model model) {

        Optional<TrimLevel> trimLevel = trimLevelRepository.findById(trimLevelId);
        Optional<Feature> feature = featureRepository.findFeatureByName(featureName);

        if (trimLevel.isEmpty() || feature.isEmpty()) {
            logger.error("TRIM LEVEL OR FEATURE NOT FOUND");
            return "redirect:trim-feature-services";
        }

        TrimFeatures trimFeatures = new TrimFeatures(feature.get(), trimLevel.get());

        trimFeatureService.addTrimFeature(trimFeatures);

        logger.info("SAVED TRIM FEATURE: {}", trimFeatures.getId());
        return "redirect:trim-feature-services";
    }

    @GetMapping("/delete-trim-feature/{trimId}/{featureName}")
    public String deleteTrimFeature(@PathVariable Long trimId, @PathVariable String featureName) {
        TrimFeatureId trimfeatureid = new TrimFeatureId(trimId, featureName);

        logger.info("DELETING TRIM FEATURE: {}", trimfeatureid);

        Optional<TrimFeatures> optionalTrimFeatures = trimFeaturesRepository.findById(trimfeatureid);
        if (optionalTrimFeatures.isEmpty()) {
            logger.error("TRIM FEATURE NOT FOUND: {}", trimfeatureid);
            return "redirect:trim-feature-services";
        }

        int response = trimFeatureService.deleteTrimFeature(trimfeatureid);
        logger.info("ROWS CHANGED IN DB: {}", response);

        return "redirect:/trim-feature-services";
    }
}
