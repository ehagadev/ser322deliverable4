package com.ser322deliverable4.repository;

import com.ser322deliverable4.composites.TrimFeatureId;
import com.ser322deliverable4.model.TrimFeatures;
import com.ser322deliverable4.model.TrimLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrimFeaturesRepository extends JpaRepository<TrimFeatures, TrimFeatureId> {

    @Query("SELECT tf.trimLevel FROM TrimFeatures tf WHERE tf.feature.name = :featureName")
    List<TrimLevel> findTrimLevelsByFeatureName(@Param("featureName") String featureName);

    @Query("SELECT tf FROM TrimFeatures tf WHERE tf.trimLevel = :trimLevel")
    List<TrimFeatures> findByTrimLevel(@Param("trimLevel") TrimLevel trimLevel);

}
