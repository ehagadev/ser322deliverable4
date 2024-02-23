package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Model;
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
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT m FROM Model m WHERE m.trimLevel = :trimLevel")
    List<Model> findByTrimLevel(@Param("trimLevel") TrimLevel trimLevel);

    @Query("SELECT m FROM Model m")
    List<Model> findAllModels();

    @Query("SELECT m FROM Model m WHERE m.name = :name")
    Optional<Model> findModelByName(String name);

    @Query("SELECT m FROM Model m WHERE m.modelId = :modelId")
    Optional<Model> findModelById(@Param("modelId") Long modelId);

    @Modifying
    @Transactional
    @Query("UPDATE Model m SET m.name = :name, m.year = :year, m.style = :style WHERE m.modelId = :modelId")
    int updateModelInfo(
            @Param("modelId") Long modelId,
            @Param("name") String name,
            @Param("year") String year,
            @Param("style") String style
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM Model m WHERE m.modelId = :modelId")
    int deleteModelById(@Param("modelId") Long modelId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Model (name, \"year\", style, trim_level) VALUES (:name, :year, :style, :trimLevel)", nativeQuery = true)
    int insertNewModel(
            @Param("name") String name,
            @Param("year") String year,
            @Param("style") String style,
            @Param("trimLevel") Long trimLevel
    );
}
