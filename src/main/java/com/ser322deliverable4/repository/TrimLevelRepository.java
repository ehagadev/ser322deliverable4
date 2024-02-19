package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.TrimLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrimLevelRepository extends JpaRepository<TrimLevel, Long> {
}
