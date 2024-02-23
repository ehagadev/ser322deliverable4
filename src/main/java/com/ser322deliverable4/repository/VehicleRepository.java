package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Model;
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
	
    @Query("SELECT v FROM Vehicle v " +
            "WHERE v.model.name = :modelName")
    List<Vehicle> findVehiclesByModelYear(@Param("modelName") String modelName);
	
    @Query("SELECT v FROM Vehicle v " +
            "WHERE v.model.year = :modelYear")
    List<Vehicle> findVehiclesBySpecificModel(@Param("modelYear") String modelYear);


    @Query("SELECT v FROM Vehicle v " + 
            "JOIN Model m ON v.model.modelId = m.modelId " +
            "WHERE year = :year")
    List<Vehicle> findVehiclesByYear(@Param("year") String year);

    @Query("SELECT v FROM Vehicle v " + 
            "JOIN Model m ON v.model.modelId = m.modelId " +
            "WHERE UPPER(m.name) = UPPER(:modelName)")
    List<Vehicle> findVehiclesByModelName(@Param("modelName") String modelName);

    @Query("SELECT v FROM Vehicle v " +
            "JOIN Model m ON v.model.modelId = m.modelId " +
            "JOIN Manufacturer ma ON m.trimLevel.manufacturer.name = ma.name " +
            "WHERE UPPER(ma.name) = UPPER(:mfg)")
    List<Vehicle> findVehiclesByMfg(@Param("mfg") String mfg);

    @Query("SELECT v FROM Vehicle v " +
            "JOIN Model m ON v.model.modelId = m.modelId " +
            "JOIN TrimLevel t ON m.trimLevel.trimId = t.trimId " +
            "JOIN Manufacturer ma ON m.trimLevel.manufacturer.name = ma.name " +
            "WHERE UPPER(ma.name) = UPPER(:mfg) " +
                "AND UPPER(t.name) = UPPER(:trimLevel) " +
                "AND UPPER(v.color) = UPPER(:color)")
        List<Vehicle> findVehiclesByMtc(@Param("mfg") String mfg, @Param("trimLevel") String trimLevel, @Param("color") String color);

    @Modifying
    @Transactional
    @Query(value = "UPDATE vehicle SET color = :color, model = :modelId WHERE vin = :vin", nativeQuery = true)
    void saveCustom(@Param("vin") String vin, @Param("color") String color, @Param("modelId") Long modelId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vehicle v WHERE v.vin = :vin")
    void deleteByVin(@Param("vin") String vin);

    @Query(value = "INSERT INTO Vehicle(vin, color, model) VALUES (:vin, :color, :model)")
    @Modifying
    @Transactional
    void insertNewVehicle(String vin, String color, Model model);
}
