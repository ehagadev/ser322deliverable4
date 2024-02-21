package com.ser322deliverable4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trim_level")
public class TrimLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trimId;

    @Column(length = 100)
    private String name;

    @Column(name = "\"year\"")
    private String year;

    @ManyToOne
    @JoinColumn(
            name = "manufacturer",
            referencedColumnName = "name"
    )
    private Manufacturer manufacturer;

    public TrimLevel() {
    }

    public TrimLevel(String name, String year, Manufacturer manufacturer) {
        this.name = name;
        this.year = year;
        this.manufacturer = manufacturer;
    }

    public Long getTrimId() {
        return trimId;
    }

    public void setTrimId(Long trimId) {
        this.trimId = trimId;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturerEntity) {
        this.manufacturer = manufacturerEntity;
    }
}
