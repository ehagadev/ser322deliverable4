package com.ser322deliverable4.service.feature;

import com.ser322deliverable4.model.Feature;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface IFeatureService {

    Feature addFeature(Feature feature);

    List<Feature> getAll();

    Feature getFeatureByName(String name);

    int editFeature(Feature editFeature);

    int deleteFeature(String featureName) throws SQLIntegrityConstraintViolationException;
}
