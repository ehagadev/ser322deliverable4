package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.ModelFeatures;
import com.ser322deliverable4.service.modelfeature.IModelFeatureService;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ModelFeatureController {
    private final IModelFeatureService modelFeatureService;

    private final Logger logger = LoggerFactory.getLogger(ModelFeatureController.class);

    public ModelFeatureController(IModelFeatureService modelFeatureService) {
        this.modelFeatureService = modelFeatureService;
    }

    @GetMapping("/model-feature-services")
    public String addModelFeaturePage(Model model) {
        ModelFeatures modelFeatures = new ModelFeatures();
        model.addAttribute("modelFeatures", modelFeatures);
        List<ModelFeatures> modelFeaturesList = modelFeatureService.getAllModelFeatures();
        model.addAttribute("modelFeaturesList", modelFeaturesList);
        return "model-feature-services";
    }

}
