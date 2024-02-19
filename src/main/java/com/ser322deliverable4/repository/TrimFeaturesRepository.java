package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.TrimFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrimFeaturesRepository extends JpaRepository<TrimFeatures, Long> {

}
