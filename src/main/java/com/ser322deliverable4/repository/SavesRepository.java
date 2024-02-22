package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Saves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavesRepository extends JpaRepository<Saves, Long> {

    @Query("SELECT s FROM Saves s WHERE s.vehicle.vin = :vin")
    List<Saves> findSavesByVehicleVin(@Param("vin") String vin);
	
	
	
    @Query("INSERT INTO Saves(userId, vin) VALUES (:userId, :vin)")
    @Modifying
    @Transactional
    void addNewSave(
            @Param("userId") long userId,
            @Param("vin") String vin
    );
	

    @Query("SELECT m FROM SAVES m WHERE m.vin = :vin")
    Optional<Saves> findByVin(@Param("vin") String vin);


    @Query("DELETE FROM Saves m WHERE m.vin = :vin")
    @Modifying
    @Transactional
    int deleteByVin(String vin);
	
}

