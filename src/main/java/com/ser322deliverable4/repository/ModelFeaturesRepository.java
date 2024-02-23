package com.ser322deliverable4.repository;

import com.ser322deliverable4.composites.ModelFeatureId;
import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.model.Model;
import com.ser322deliverable4.model.ModelFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ModelFeaturesRepository extends JpaRepository<ModelFeatures, ModelFeatureId> {

    @Query("INSERT INTO ModelFeatures(id, model, feature) VALUES (:id, :model, :feature)")
    @Modifying
    @Transactional
    void insertNewModelFeature(
            @Param("id") ModelFeatureId id,
            @Param("model") Model model,
            @Param("feature") Feature feature
    );


    @Query("SELECT mf FROM ModelFeatures mf WHERE mf.id = :modelFeatureId")
    Optional<ModelFeatures> findModelFeatureById(ModelFeatureId modelFeatureId);

    @Query("UPDATE ModelFeatures mf SET mf.model = :model, mf.feature = :feature WHERE mf.id = :id")
    @Modifying
    @Transactional
    int updateModelFeature(ModelFeatureId id, Model model, Feature feature);


    @Query("DELETE FROM ModelFeatures mf WHERE mf.id = :modelFeatureId")
    @Modifying
    @Transactional
    int deleteModelFeatureById(@Param("modelFeatureId") ModelFeatureId modelFeatureId);
}
