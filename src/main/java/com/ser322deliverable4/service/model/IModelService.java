package com.ser322deliverable4.service.model;

import com.ser322deliverable4.model.Model;

import java.sql.SQLIntegrityConstraintViolationException;

public interface IModelService {
    int editModel(Model editModel) throws Exception;

    int deleteModel(Long modelId) throws SQLIntegrityConstraintViolationException;
}
