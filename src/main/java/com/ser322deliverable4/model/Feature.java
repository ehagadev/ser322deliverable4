package com.ser322deliverable4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @NotNull
    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    public Feature() {
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
