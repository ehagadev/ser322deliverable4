package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Saves;
import com.ser322deliverable4.model.User;
import com.ser322deliverable4.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Repository
public interface SavesRepository extends JpaRepository<Saves, Long> {


    @Query("SELECT s FROM Saves s WHERE s.vehicle.vin = :vin")
    List<Saves> findSavesByVehicleVin(@Param("vin") String vin);


    @Query("SELECT s FROM Saves s WHERE s.user.id = :userId")
    List<Saves> findSavesByUserId(@Param("userId") Long userId);


	@Modifying
    @Query("INSERT INTO Saves(user, vehicle) VALUES (:user, :vehicle)")
    @Transactional
    void addNewSave(
            @Param("user") User user,
            @Param("vehicle") Vehicle vehicle
    );
	

    @Query("DELETE FROM Saves s WHERE s.vehicle.vin = :vin")
    @Modifying
    @Transactional
    int deleteSaveByVehicleVin(String vin);
	
	
    @Query("DELETE FROM Saves s WHERE s.user.id = :userId")
    @Modifying
    @Transactional
    int deleteSaveByUserId(@Param("userId") Long userId);



}


