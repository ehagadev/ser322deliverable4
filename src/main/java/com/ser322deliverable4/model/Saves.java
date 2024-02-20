package com.ser322deliverable4.model;

import com.ser322deliverable4.composites.SavesId;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "saves")
public class Saves {

    @EmbeddedId
    private SavesId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @MapsId("vehicleVin")
    @ManyToOne
    @JoinColumn(name = "vehicleVin", referencedColumnName = "vin")
    private Vehicle vehicle;

    public Saves() {
    }

    public Saves(User user, Vehicle vehicle) {
        this.user = user;
        this.vehicle = vehicle;
        this.id = new SavesId(user.getId(), vehicle.getVin());
    }

    public SavesId getId() {
        return id;
    }

    public void setId(SavesId id) {
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