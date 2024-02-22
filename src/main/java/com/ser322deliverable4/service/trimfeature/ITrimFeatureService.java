package com.ser322deliverable4.service.trimfeature;

import com.ser322deliverable4.composites.TrimFeatureId;
import com.ser322deliverable4.model.TrimFeatures;

import java.util.List;

public interface ITrimFeatureService {

    TrimFeatures addTrimFeature(TrimFeatures trimFeature);

    List<TrimFeatures> getAllTrimFeatures();

    TrimFeatures getTrimFeatureById(TrimFeatureId trimFeatureId);

    int editTrimFeature(TrimFeatures editedTrimFeature);

    int deleteTrimFeature(TrimFeatureId trimFeatureId);
}
