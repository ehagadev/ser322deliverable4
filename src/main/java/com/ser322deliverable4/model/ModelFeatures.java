package com.ser322deliverable4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "model_features")
public class ModelFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "\"model\"", referencedColumnName = "modelId")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "feature", referencedColumnName = "name")
    private Feature feature;

    public ModelFeatures() {
    }

    public ModelFeatures(Model model, Feature feature) {
        this.model = model;
        this.feature = feature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
