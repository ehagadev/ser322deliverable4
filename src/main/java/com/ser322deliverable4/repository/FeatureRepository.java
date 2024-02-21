package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Feature;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, String> {


    @Query("INSERT INTO Feature(name, description) VALUES (:name, :description)")
    @Modifying
    @Transactional
    void insertNewFeature(
            @Param("name") String name,
            @Param("description") String description
    );

    @Query("SELECT f FROM Feature f WHERE f.name = :name")
    Optional<Feature> findFeatureByName(@Param("name") String name);

}