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

    @Column(length = 100)
    private String manufacturer;

    @Column
    private int year;

    @ManyToOne
    @JoinColumn(
            name = "Manufacturer",
            referencedColumnName = "name",
            insertable = false,
            updatable = false
    )
    private Manufacturer manufacturerEntity;

    public TrimLevel() {
    }
}
