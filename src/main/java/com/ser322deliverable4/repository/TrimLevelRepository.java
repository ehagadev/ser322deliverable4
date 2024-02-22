package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Manufacturer;
import com.ser322deliverable4.model.TrimLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrimLevelRepository extends JpaRepository<TrimLevel, Long> {

    @Query("SELECT t FROM TrimLevel t")
    List<TrimLevel> findAllTrimLevels();

    @Query(value="INSERT INTO TRIM_LEVEL(name, \"year\", manufacturer) VALUES (:name, :year, :manufacturer)", nativeQuery=true)
    @Modifying
    @Transactional
    void insertNewTrimLevel(
            @Param("name") String name,
            @Param("year") String year,
            @Param("manufacturer") String manufacturer
    );

    @Query(value="SELECT t FROM TrimLevel t WHERE t.trimId = :trimId")
    Optional<TrimLevel> findTrimLevelById(@Param("trimId") Long trimId);

    @Modifying
    @Transactional
    @Query("update TrimLevel set name = :name, year = :year, manufacturer = :manufacturer where trimId = :trimId")
    int updateTrimLevelByTrimId(@Param("trimId") Long trimId, @Param("name") String name, @Param("year") String year, @Param("manufacturer") Manufacturer manufacturer);


    @Query("DELETE FROM TrimLevel t WHERE t.trimId = :trimId")
    @Modifying
    @Transactional
    int deleteTrimLevelById(@Param("trimId") Long trimId);

    @Query("SELECT t FROM TrimLevel t WHERE t.name = :name")
    Optional<TrimLevel> findTrimLevelByName(@Param("name") String name);

}
