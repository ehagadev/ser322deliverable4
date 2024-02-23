package com.ser322deliverable4.controller;

import com.ser322deliverable4.composites.ModelFeatureId;
import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.model.ModelFeatures;
import com.ser322deliverable4.repository.FeatureRepository;
import com.ser322deliverable4.repository.ModelRepository;
import com.ser322deliverable4.service.modelfeature.IModelFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import com.ser322deliverable4.repository.ModelFeaturesRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelFeatureController {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private FeatureRepository featureRepository;

    private final IModelFeatureService modelFeatureService;

    @Autowired
    private ModelFeaturesRepository modelFeaturesRepository;

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

        List<com.ser322deliverable4.model.Model> models = modelRepository.findAll();
        model.addAttribute("models", models);

        List<Feature> features = featureRepository.findAll();
        model.addAttribute("features", features);

        return "model-feature-services";
    }

    @GetMapping("/delete-model-feature/{modelId}/{featureName}")
    public String deleteModelFeature(@PathVariable Long modelId, @PathVariable String featureName) {
        ModelFeatureId modelfeatureid = new ModelFeatureId(modelId, featureName);

        logger.info("DELETING MODEL FEATURE: {}", modelfeatureid);

        Optional<ModelFeatures> optionalModelFeatures = modelFeaturesRepository.findById(modelfeatureid);
        if (optionalModelFeatures.isEmpty()) {
            logger.error("MODEL FEATURE NOT FOUND: {}", modelfeatureid);
            return "redirect:model-feature-services";
        }

        int response = modelFeatureService.deleteModelFeature(modelfeatureid);
        logger.info("ROWS CHANGED IN DB: {}", response);

        return "redirect:/model-feature-services";
    }

    @PostMapping("/add-model-feature")
    public String addModelFeature(@RequestParam("modelId") Long modelId, @RequestParam("featureName") String featureName, Model unusedModel) {

        Optional<com.ser322deliverable4.model.Model> model = modelRepository.findById(modelId);
        Optional<Feature> feature = featureRepository.findFeatureByName(featureName);

        if (model.isEmpty() || feature.isEmpty()) {
            logger.error("MODEL OR FEATURE NOT FOUND");
            return "redirect:model-feature-services";
        }

        ModelFeatures modelFeatures = new ModelFeatures(model.get(), feature.get());

        modelFeatureService.addModelFeature(modelFeatures);

        logger.info("SAVED MODEL FEATURE: {}", modelFeatures.getId());
        return "redirect:model-feature-services";
    }
}
