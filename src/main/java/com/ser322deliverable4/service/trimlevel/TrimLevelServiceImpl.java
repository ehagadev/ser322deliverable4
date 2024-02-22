package com.ser322deliverable4.service.trimlevel;

import com.ser322deliverable4.model.Model;
import com.ser322deliverable4.model.TrimFeatures;
import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.repository.ModelRepository;
import com.ser322deliverable4.repository.TrimFeaturesRepository;
import com.ser322deliverable4.repository.TrimLevelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrimLevelServiceImpl implements ITrimLevelService {

    private final TrimLevelRepository trimLevelRepository;
    private final ModelRepository modelRepository;
    private final TrimFeaturesRepository trimFeaturesRepository;
    private final Logger logger = LoggerFactory.getLogger(TrimLevelServiceImpl.class);

    public TrimLevelServiceImpl(TrimLevelRepository trimLevelRepository,
                                ModelRepository modelRepository,
                                TrimFeaturesRepository trimFeaturesRepository) {
        this.trimLevelRepository = trimLevelRepository;
        this.modelRepository = modelRepository;
        this.trimFeaturesRepository = trimFeaturesRepository;
    }

    @Override
    public TrimLevel addTrimLevel(TrimLevel trimLevel) {
        trimLevelRepository.insertNewTrimLevel(
                trimLevel.getName(), trimLevel.getYear(), trimLevel.getManufacturer().getName()
        );
        logger.info("SUCCESSFULLY ADDED NEW TRIM LEVEL {} INTO THE DB", trimLevel.getName());
        return trimLevel;
    }

    @Override
    public List<TrimLevel> getAll() {
        logger.info("FETCHING ALL TRIM LEVELS");
        return trimLevelRepository.findAll();
    }

    @Override
    public TrimLevel getTrimLevelById(Long trimId) {
        logger.info("ATTEMPTING TO FIND TRIM LEVEL BY ID: {}", trimId);
        Optional<TrimLevel> byId = trimLevelRepository.findTrimLevelById(trimId);
        if (byId.isPresent()) {
            TrimLevel trimLevel = byId.get();
            logger.info("FOUND TRIM LEVEL BY ID: {}", trimLevel.getName());
            return trimLevel;
        } else {
            logger.error("TRIM LEVEL NOT FOUND BY ID: {}", trimId);
            return null;
        }
    }

    @Override
    public List<TrimLevel> getAllTrimLevels() {
        logger.info("FETCHING ALL TRIM LEVELS");
        return trimLevelRepository.findAll();

    }

    @Override
    public int editTrimLevel(TrimLevel trimLevel) {
        logger.info("AT BEGINNING OF EDIT TRIM LEVEL");
        Optional<TrimLevel> byTrimId = trimLevelRepository.findTrimLevelById(trimLevel.getTrimId());
        if (byTrimId.isPresent()) {
            int updatedRows = trimLevelRepository.updateTrimLevelByTrimId(trimLevel.getTrimId(), trimLevel.getName(), trimLevel.getYear(), trimLevel.getManufacturer());
            logger.info("SUCCESSFULLY EDITED TRIM LEVEL: {}, UPDATED ROWS: {}", trimLevel.getName(), updatedRows);
            return 1;
        } else {
            logger.error("TRIM LEVEL NOT FOUND BY ID: {}", trimLevel.getTrimId());
            return 0;
        }
    }

    @Override
    public int deleteTrimLevel(Long trimId) {
        Optional<TrimLevel> byTrimId = trimLevelRepository.findTrimLevelById(trimId);
        if (byTrimId.isPresent()) {
            int response = trimLevelRepository.deleteTrimLevelById(trimId);
            logger.info("SUCCESSFULLY DELETED TRIM LEVEL BY ID: {}", trimId);
            return response;
        } else {
            logger.error("UNABLE TO FIND TRIM LEVEL BY ID: {}", trimId);
            return 0;
        }
    }
}
