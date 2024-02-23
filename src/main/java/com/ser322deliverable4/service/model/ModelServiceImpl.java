package com.ser322deliverable4.service.model;

import com.ser322deliverable4.model.Model;
import com.ser322deliverable4.repository.ModelRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class ModelServiceImpl implements IModelService {

    private final ModelRepository modelRepository;
    private final Logger logger = LoggerFactory.getLogger(ModelServiceImpl.class);

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public int editModel(Model editModel) throws Exception {
        try {
            int response = modelRepository.updateModelInfo(
                    editModel.getModelId(),
                    editModel.getName(),
                    editModel.getYear(),
                    editModel.getStyle()
            );
            return response;
        } catch (Exception e) {
            logger.error("UNABLE TO EDIT MODEL BY ID: "+ editModel.getModelId());
            throw new Exception("UNABLE TO EDIT MODEL BY ID: "+ editModel.getModelId());
        }
    }

    @Override
    public int deleteModel(Long modelId) throws SQLIntegrityConstraintViolationException {
        Optional<Model> byId = modelRepository.findModelById(modelId);
        if (byId.isPresent()) {
            Model model = byId.get();
            try {
                int response = modelRepository.deleteModelById(model.getModelId());
                logger.info("SUCCESSFULLY DELETED MODEL BY ID: {}", model.getModelId());
                return response;
            } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
                logger.error("UNABLE TO DELETE MODEL: {}. It has associated references.", modelId);
                throw new SQLIntegrityConstraintViolationException("Unable to delete model. It has associated references", ex);
            }
        } else {
            logger.error("UNABLE TO FIND MODEL BY ID: {}", modelId);
            return 0;
        }
    }
}
