package com.ser322deliverable4.service.trimlevel;

import com.ser322deliverable4.model.TrimLevel;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface ITrimLevelService {

    TrimLevel addTrimLevel(TrimLevel trimLevel);

    List<TrimLevel> getAll();

    TrimLevel getTrimLevelById(Long trimId);

    List<TrimLevel> getAllTrimLevels();

    int editTrimLevel(TrimLevel trimLevel);

    int deleteTrimLevel(Long trimId) throws SQLIntegrityConstraintViolationException;
}
