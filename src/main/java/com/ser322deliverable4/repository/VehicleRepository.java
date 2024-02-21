package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    /**
     * returns wrapped in Optional in case User is null
     * @param vin
     * @return Vehicle
     */
    @Query("SELECT v FROM Vehicle v WHERE v.vin = :vin")
    Optional<Vehicle> findVehicleByVin(@Param("vin") String vin);

}
