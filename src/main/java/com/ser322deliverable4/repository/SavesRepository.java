package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Saves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavesRepository extends JpaRepository<Saves, Long> {

    @Query("SELECT s FROM Saves s WHERE s.vehicle.vin = :vin")
    List<Saves> findSavesByVehicleVin(@Param("vin") String vin);
}
