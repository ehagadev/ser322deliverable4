package com.ser322deliverable4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trim_features")
public class TrimFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feature", referencedColumnName = "name")
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "trim_level", referencedColumnName = "trimId")
    private TrimLevel trimLevel;

    public TrimFeatures() {
    }

    public TrimFeatures(Feature feature, TrimLevel trimLevel) {
        this.feature = feature;
        this.trimLevel = trimLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public TrimLevel getTrimLevel() {
        return trimLevel;
    }

    public void setTrimLevel(TrimLevel trimLevel) {
        this.trimLevel = trimLevel;
    }
}
