package com.ser322deliverable4.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "saves")
public class Saves {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "\"user\"", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vin", referencedColumnName = "vin")
    private Vehicle vehicle;

    public Saves() {
    }

    public Saves(User user, Vehicle vehicle) {
        this.user = user;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
