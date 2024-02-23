package com.ser322deliverable4.service.modelfeature;

import com.ser322deliverable4.composites.ModelFeatureId;
import com.ser322deliverable4.model.ModelFeatures;

import java.util.List;

public interface IModelFeatureService {

    ModelFeatures addModelFeature(ModelFeatures modelFeature);

    List<ModelFeatures> getAllModelFeatures();

    ModelFeatures getModelFeatureById(ModelFeatureId modelFeatureId);

    int editModelFeature(ModelFeatures editedModelFeature);

    int deleteModelFeature(ModelFeatureId modelFeatureId);

}
