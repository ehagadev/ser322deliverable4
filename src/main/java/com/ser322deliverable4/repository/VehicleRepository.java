package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    /**
     * returns wrapped in Optional in case User is null
     *
     * @param vin
     * @return Vehicle
     */
    @Query("SELECT v FROM Vehicle v WHERE v.vin = :vin")
    Optional<Vehicle> findVehicleByVin(@Param("vin") String vin);


    @Query("SELECT v FROM Vehicle v " +
            "JOIN ModelFeatures mf ON v.model.modelId = mf.model.modelId " +
            "JOIN Feature f ON mf.feature.name = f.name " +
            "WHERE f.name = :featureName")
    List<Vehicle> findVehicleByFeatureName(@Param("featureName") String featureName);

    @Query("SELECT v FROM Vehicle v " +
            "JOIN Model m ON v.model.modelId = m.modelId " +
            "JOIN TrimLevel t ON m.trimLevel.trimId = t.trimId " +
            "WHERE t.name = :trimLevelName")
    List<Vehicle> findVehiclesByTrimLevelName(@Param("trimLevelName") String trimLevelName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE vehicle SET color = :color, model = :modelId WHERE vin = :vin", nativeQuery = true)
    void saveCustom(@Param("vin") String vin, @Param("color") String color, @Param("modelId") Long modelId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vehicle v WHERE v.vin = :vin")
    void deleteByVin(@Param("vin") String vin);
}
