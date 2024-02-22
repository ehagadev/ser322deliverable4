package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Query("INSERT INTO Manufacturer(name, country) VALUES (:name, :country)")
    @Modifying
    @Transactional
    void insertNewManufacturer(
            @Param("name") String name,
            @Param("country") String country
    );

    @Query("SELECT m FROM Manufacturer m WHERE m.name = :name")
    Optional<Manufacturer> findByName(@Param("name") String name);

    @Query("DELETE FROM Manufacturer m WHERE m.name = :name")
    @Modifying
    @Transactional
    int deleteByName(String name);
}
