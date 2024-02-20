package com.ser322deliverable4.composites;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ModelFeatureId implements Serializable {
    private Long modelId;
    private String featureName;

    public ModelFeatureId() {}

    public ModelFeatureId(Long modelId, String name) {
        this.modelId = modelId;
        this.featureName = name;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelFeatureId that)) return false;
        return Objects.equals(modelId, that.modelId) &&
                Objects.equals(featureName, that.featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, featureName);
    }


}