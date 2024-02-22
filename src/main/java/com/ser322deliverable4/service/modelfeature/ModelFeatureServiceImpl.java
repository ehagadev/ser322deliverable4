package com.ser322deliverable4.service.modelfeature;

import com.ser322deliverable4.composites.ModelFeatureId;
import com.ser322deliverable4.model.ModelFeatures;
import com.ser322deliverable4.repository.ModelFeaturesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelFeatureServiceImpl implements IModelFeatureService {

    private final ModelFeaturesRepository modelFeaturesRepository;

    private final Logger logger = LoggerFactory.getLogger(ModelFeatureServiceImpl.class);

    public ModelFeatureServiceImpl(ModelFeaturesRepository modelFeaturesRepository) {
        this.modelFeaturesRepository = modelFeaturesRepository;
    }

    @Override
    public ModelFeatures addModelFeature(ModelFeatures modelFeature) {
        modelFeaturesRepository.insertNewModelFeature(
                modelFeature.getId(), modelFeature.getModel(), modelFeature.getFeature()
        );
        logger.info("SUCCESSFULLY ADDED NEW MODEL FEATURE '{} - {}' INTO THE DB", modelFeature.getModel(), modelFeature.getFeature());
        return modelFeature;
    }

    @Override
    public List<ModelFeatures> getAllModelFeatures() {
        logger.info("FETCHING ALL MODEL FEATURES");
        return modelFeaturesRepository.findAll();
    }

    @Override
    public ModelFeatures getModelFeatureById(ModelFeatureId modelFeatureId) {
        logger.info("ATTEMPTING TO FIND MODEL FEATURE BY ID: {}", modelFeatureId);
        Optional<ModelFeatures> byId = modelFeaturesRepository.findModelFeatureById(modelFeatureId);

        if (byId.isPresent()) {
            ModelFeatures modelFeature = byId.get();
            logger.info("FOUND MODEL FEATURE BY ID: {}", modelFeature.getId());
            return modelFeature;
        } else {
            logger.error("MODEL FEATURE NOT FOUND BY ID: {}", modelFeatureId);
            return null;
        }
    }

    @Override
    public int editModelFeature(ModelFeatures editedModelFeature) {
        logger.info("AT BEGINNING OF EDIT MODEL FEATURE");
        Optional<ModelFeatures> byId = modelFeaturesRepository.findModelFeatureById(editedModelFeature.getId());
        if (byId.isPresent()) {
            ModelFeatures modelFeature = byId.get();
            modelFeature.setModel(editedModelFeature.getModel());
            modelFeature.setFeature(editedModelFeature.getFeature());
            int saved = modelFeaturesRepository.updateModelFeature(modelFeature.getId(), modelFeature.getModel(), modelFeature.getFeature());
            return saved;
        } else {
            logger.error("MODEL FEATURE NOT FOUND BY ID: {}", editedModelFeature.getId());
            return 0;
        }
    }

    @Override
    public int deleteModelFeature(ModelFeatureId modelFeatureId) {
        Optional<ModelFeatures> byId = modelFeaturesRepository.findModelFeatureById(modelFeatureId);
        if (byId.isPresent()) {
            ModelFeatures modelFeature = byId.get();
            int response = modelFeaturesRepository.deleteModelFeatureById(modelFeature.getId());
            return response;
        } else {
            logger.error("UNABLE TO FIND MODEL FEATURE BY ID: {}", modelFeatureId);
            return 0;
        }
    }
}
