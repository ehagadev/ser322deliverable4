package com.ser322deliverable4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @Column(length = 50)
    private String vin;

    @ManyToOne
    @JoinColumn(name = "model", referencedColumnName = "modelId")
    private Model model;

    @Column(length = 50)
    private String color;

    public Vehicle() {
    }

    public Vehicle(String vin, Model model, String color) {
        this.vin = vin;
        this.model = model;
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", model=" + model +
                ", color='" + color + '\'' +
                '}';
    }

}
