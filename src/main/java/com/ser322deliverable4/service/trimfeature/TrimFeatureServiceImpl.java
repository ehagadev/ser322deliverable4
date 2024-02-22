package com.ser322deliverable4.service.trimfeature;

import com.ser322deliverable4.composites.TrimFeatureId;
import com.ser322deliverable4.model.TrimFeatures;
import com.ser322deliverable4.repository.TrimFeaturesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrimFeatureServiceImpl implements ITrimFeatureService {

    private final TrimFeaturesRepository trimFeaturesRepository;

    private final Logger logger = LoggerFactory.getLogger(TrimFeatureServiceImpl.class);

    public TrimFeatureServiceImpl(TrimFeaturesRepository trimFeaturesRepository) {
        this.trimFeaturesRepository = trimFeaturesRepository;
    }


    @Override
    public TrimFeatures addTrimFeature(TrimFeatures trimFeature) {
        trimFeaturesRepository.save(trimFeature);
//        trimFeaturesRepository.insertNewTrimFeature(
//                trimFeature.getId(), trimFeature.getTrimLevel(), trimFeature.getFeature()
//        );
        // raw sql here is throwing an error due to "multiple tables" via compose key
        logger.info("SUCCESSFULLY ADDED NEW TRIM FEATURE '{} - {}' INTO THE DB", trimFeature.getTrimLevel(), trimFeature.getFeature());
        return trimFeature;
    }

    @Override
    public List<TrimFeatures> getAllTrimFeatures() {
        logger.info("FETCHING ALL TRIM FEATURES");
        return trimFeaturesRepository.findAll();
    }

    @Override
    public TrimFeatures getTrimFeatureById(TrimFeatureId trimFeatureId) {
        logger.info("ATTEMPTING TO FIND TRIM FEATURE BY ID: {}", trimFeatureId);
        Optional<TrimFeatures> byId = trimFeaturesRepository.findTrimFeatureById(trimFeatureId);

        if (byId.isPresent()) {
            TrimFeatures trimFeature = byId.get();
            logger.info("FOUND TRIM FEATURE BY ID: {}", trimFeature.getId());
            return trimFeature;
        } else {
            logger.error("TRIM FEATURE NOT FOUND BY ID: {}", trimFeatureId);
            return null;
        }
    }

    @Override
    public int editTrimFeature(TrimFeatures editedTrimFeature) {
        logger.info("AT BEGINNING OF EDIT TRIM FEATURE");
        Optional<TrimFeatures> byId = trimFeaturesRepository.findTrimFeatureById(editedTrimFeature.getId());
        if (byId.isPresent()) {
            TrimFeatures trimFeature = byId.get();
            trimFeature.setTrimLevel(editedTrimFeature.getTrimLevel());
            trimFeature.setFeature(editedTrimFeature.getFeature());
            int saved = trimFeaturesRepository.updateTrimFeature(trimFeature.getId(), trimFeature.getTrimLevel(), trimFeature.getFeature());
            return saved;
        } else {
            logger.error("TRIM FEATURE NOT FOUND BY ID: {}", editedTrimFeature.getId());
            return 0;
        }
    }

    @Override
    public int deleteTrimFeature(TrimFeatureId trimFeatureId) {
        Optional<TrimFeatures> byId = trimFeaturesRepository.findTrimFeatureById(trimFeatureId);
        if (byId.isPresent()) {
            TrimFeatures trimFeature = byId.get();
            int response = trimFeaturesRepository.deleteTrimFeatureById(trimFeature.getId());
            return response;
        } else {
            logger.error("TRIM FEATURE NOT FOUND BY ID: {}", trimFeatureId);
            return 0;
        }
    }
}

