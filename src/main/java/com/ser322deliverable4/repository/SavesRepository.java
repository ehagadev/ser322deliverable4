package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.Saves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavesRepository extends JpaRepository<Saves, Long> {

}
