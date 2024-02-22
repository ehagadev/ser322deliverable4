package com.ser322deliverable4.repository;

import com.ser322deliverable4.composites.TrimFeatureId;
import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.model.TrimFeatures;
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
public interface TrimFeaturesRepository extends JpaRepository<TrimFeatures, TrimFeatureId> {


    @Query("INSERT INTO TrimFeatures(id, trimLevel, feature) VALUES (:id, :trimLevel, :feature)")
    @Modifying
    @Transactional
    void insertNewTrimFeature(
            @Param("id") TrimFeatureId id,
            @Param("trimLevel") TrimLevel trimLevel, // adjusted the parameter name to "trimLevel"
            @Param("feature") Feature feature
    );

    @Query("SELECT tf.trimLevel FROM TrimFeatures tf WHERE tf.feature.name = :featureName")
    List<TrimLevel> findTrimLevelsByFeatureName(@Param("featureName") String featureName);

    @Query("SELECT tf FROM TrimFeatures tf WHERE tf.trimLevel = :trimLevel")
    List<TrimFeatures> findByTrimLevel(@Param("trimLevel") TrimLevel trimLevel);


    @Query("SELECT tf FROM TrimFeatures tf WHERE tf.id = :trimFeatureId")
    Optional<TrimFeatures> findTrimFeatureById(TrimFeatureId trimFeatureId);

    @Query("UPDATE TrimFeatures tf SET tf.trimLevel = :trim, tf.feature = :feature WHERE tf.id = :id")
    @Modifying
    @Transactional
    int updateTrimFeature(TrimFeatureId id, TrimLevel trim, Feature feature);

    @Query("DELETE FROM TrimFeatures tf WHERE tf.id = :trimFeatureId")
    @Modifying
    @Transactional
    int deleteTrimFeatureById(@Param("trimFeatureId") TrimFeatureId id);
}
