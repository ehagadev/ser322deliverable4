package com.ser322deliverable4.composites;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TrimFeatureId implements Serializable {
    private Long trimId;
    private String featureName;

    public TrimFeatureId() {}

    public TrimFeatureId(Long trimId, String name) {
        this.trimId = trimId;
        this.featureName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimFeatureId that = (TrimFeatureId) o;
        return Objects.equals(trimId, that.trimId) && Objects.equals(featureName, that.featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trimId, featureName);
    }

    public Long getTrimId() {
        return trimId;
    }

    public void setTrimId(Long trimId) {
        this.trimId = trimId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "TrimFeatureId{" +
                "trimId=" + trimId +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}