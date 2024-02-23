package com.ser322deliverable4.model;

import com.ser322deliverable4.composites.ModelFeatureId;
import jakarta.persistence.*;


@Entity
@Table(name = "model_features")
public class ModelFeatures {

    @EmbeddedId
    private ModelFeatureId id;

    @MapsId("modelId")
    @ManyToOne
    @JoinColumn(name="modelId", referencedColumnName = "modelId")
    private Model model;

    @MapsId("featureName")
    @ManyToOne
    @JoinColumn(name="featureName", referencedColumnName = "name")
    private Feature feature;

    public ModelFeatures() {
    }

    public ModelFeatures(Model model, Feature feature) {
        this.model = model;
        this.feature = feature;
        this.id = new ModelFeatureId(model.getModelId(), feature.getName());
    }

    public ModelFeatureId getId() {
        return id;
    }

    public void setId(ModelFeatureId id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "ModelFeatures{" +
                "id=" + id +
                ", model=" + model +
                ", feature=" + feature +
                '}';
    }
}
