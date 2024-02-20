package com.ser322deliverable4.model;

import com.ser322deliverable4.composites.TrimFeatureId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "trim_features")
public class TrimFeatures {

    @EmbeddedId
    private TrimFeatureId id;

    @MapsId("trimId")
    @ManyToOne
    @JoinColumn(name="trimId", referencedColumnName = "trimId")
    private TrimLevel trimLevel;

    @MapsId("featureName")
    @ManyToOne
    @JoinColumn(name="featureName", referencedColumnName = "name")
    private Feature feature;

    public TrimFeatures() {
    }

    public TrimFeatures(Feature feature, TrimLevel trimLevel) {
        this.trimLevel = trimLevel;
        this.feature = feature;
        this.id = new TrimFeatureId(trimLevel.getTrimId(), feature.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimFeatures that = (TrimFeatures) o;
        return Objects.equals(id, that.id) && Objects.equals(trimLevel, that.trimLevel) && Objects.equals(feature, that.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trimLevel, feature);
    }

    public TrimFeatureId getId() {
        return id;
    }

    public void setId(TrimFeatureId id) {
        this.id = id;
    }

    public TrimLevel getTrimLevel() {
        return trimLevel;
    }

    public void setTrimLevel(TrimLevel trimLevel) {
        this.trimLevel = trimLevel;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
