package com.ser322deliverable4.composites;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SavesId implements Serializable {
    private Long userId;
    private String vehicleVin;

    public SavesId() {}

    public SavesId(Long userId, String vehicleVin) {
        this.userId = userId;
        this.vehicleVin = vehicleVin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String modelVin) {
        this.vehicleVin = modelVin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavesId that)) return false;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(vehicleVin, that.vehicleVin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, vehicleVin);
    }
}
