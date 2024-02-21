package com.ser322deliverable4.service.feature;

import com.ser322deliverable4.model.Feature;
import com.ser322deliverable4.repository.FeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FeatureServiceImpl implements IFeatureService {

    private final FeatureRepository featureRepository;

    private final Logger logger = LoggerFactory.getLogger(FeatureServiceImpl.class);

    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Feature addFeature(Feature feature) {
        featureRepository.insertNewFeature(
                feature.getName(), feature.getDescription()
        );
        logger.info("SUCCESSFULLY ADDED NEW FEATURE {} INTO THE DB", feature.getName());
        return feature;
    }

    @Override
    public List<Feature> getAll() {
        logger.info("FETCHING ALL FEATURES");
        return featureRepository.findAll();
    }

    @Override
    public Feature getFeatureByName(String name) {
        logger.info("ATTEMPTING TO FIND FEATURE BY NAME: {}", name);
        Optional<Feature> byName = featureRepository.findFeatureByName(name);

        if (byName.isPresent()) {
            Feature feature = byName.get();
            logger.info("FOUND FEATURE BY NAME: {}", feature.getName());
            return feature;
        } else {
            logger.error("FEATURE NOT FOUND BY NAME: {}", name);
            return null;
        }
    }
}
