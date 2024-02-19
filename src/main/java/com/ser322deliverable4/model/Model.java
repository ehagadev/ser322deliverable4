package com.ser322deliverable4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;

    @Column(length = 100)
    private String name;

    @Column(name = "\"year\"")
    private String year;

    @Column(length = 100)
    private String style;

    @ManyToOne
    @JoinColumn(
            name = "manufacturer",
            referencedColumnName = "name"
    )
    private Manufacturer manufacturerEntity;

    @ManyToOne
    @JoinColumn(
            name = "trim_level",
            referencedColumnName = "trimId"
    )
    private TrimLevel trimLevel;

    public Model() {
    }

    public Model(String name, String year, String style, Manufacturer manufacturerEntity, TrimLevel trimLevel) {
        this.name = name;
        this.year = year;
        this.style = style;
        this.manufacturerEntity = manufacturerEntity;
        this.trimLevel = trimLevel;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Manufacturer getManufacturerEntity() {
        return manufacturerEntity;
    }

    public void setManufacturerEntity(Manufacturer manufacturerEntity) {
        this.manufacturerEntity = manufacturerEntity;
    }

    public TrimLevel getTrimLevel() {
        return trimLevel;
    }

    public void setTrimLevel(TrimLevel trimLevel) {
        this.trimLevel = trimLevel;
    }
}
