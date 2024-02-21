package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Model;
import com.ser322deliverable4.model.TrimLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT m FROM Model m WHERE m.trimLevel = :trimLevel")
    List<Model> findByTrimLevel(@Param("trimLevel") TrimLevel trimLevel);
}
