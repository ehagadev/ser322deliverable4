package com.ser322deliverable4.service.trimlevel;

import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.repository.TrimLevelRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrimLevelServiceImpl implements ITrimLevelService {

    private final TrimLevelRepository trimLevelRepository;

    private final Logger logger = LoggerFactory.getLogger(TrimLevelServiceImpl.class);

    public TrimLevelServiceImpl(TrimLevelRepository trimLevelRepository) {
        this.trimLevelRepository = trimLevelRepository;
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

}
