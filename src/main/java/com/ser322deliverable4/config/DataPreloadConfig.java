package com.ser322deliverable4.config;

import com.ser322deliverable4.model.*;
import com.ser322deliverable4.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataPreloadConfig {

    @Bean
    public CommandLineRunner preloadData(UserRepository userRepository,
                                         ManufacturerRepository manufacturerRepository,
                                         TrimLevelRepository trimLevelRepository,
                                         FeatureRepository featureRepository,
                                         ModelRepository modelRepository,
                                         VehicleRepository vehicleRepository,
                                         SavesRepository savesRepository,
                                         ModelFeaturesRepository modelFeaturesRepository,
                                         TrimFeaturesRepository trimFeaturesRepository) {
        return args -> {
            // Preload Users
            User user1 = new User("secretWindowsFan@apple.com", "Steve", "Jobs");
            User savedUser1 = userRepository.save(user1);
            User user2 = new User("WindowsFTW@windows.com", "Bill", "Gates");
            User savedUser2 = userRepository.save(user2);

            // Preload Manufacturers
            Manufacturer manufacturer1 = new Manufacturer("Honda", "Japan");
            Manufacturer savedMan1 = manufacturerRepository.save(manufacturer1);
            Manufacturer manufacturer2 = new Manufacturer("Chevrolet", "USA");
            Manufacturer savedMan2 = manufacturerRepository.save(manufacturer2);

            // Preload Trim Levels
            TrimLevel trimLevel1 = new TrimLevel("Z71", "2023", savedMan1);
            TrimLevel savedTrimLevel1 = trimLevelRepository.save(trimLevel1);
            TrimLevel trimLevel2 = new TrimLevel("M Sport", "2022", savedMan2);
            TrimLevel savedTrimLevel2 = trimLevelRepository.save(trimLevel2);

            // Preload Features
            Feature feature1 = new Feature("Automatic Climate Control", "Maintains a set climate in the vehicle");
            Feature savedFeature1 = featureRepository.save(feature1);
            Feature feature2 = new Feature("Advanced Safety Package", "Includes collision warning and lane departure warning");
            Feature savedFeature2 = featureRepository.save(feature2);

            // Preload Models
            Model model1 = new Model("Civic", "2023", "Sedan", savedTrimLevel1);
            Model savedModel1 = modelRepository.save(model1);
            Model model2 = new Model("Silverado", "2022", "Pickup", savedTrimLevel2);
            Model savedModel2 = modelRepository.save(model2);

            // Preload Vehicles
            Vehicle vehicle1 = new Vehicle("111111111111", savedModel1, "Red");
            Vehicle savedVehicle1 = vehicleRepository.save(vehicle1);
            Vehicle vehicle2 = new Vehicle("222222222222", savedModel2, "Blue");
            Vehicle savedVehicle2 = vehicleRepository.save(vehicle2);

            // Preload Saves
            Saves saves1 = new Saves(savedUser1, savedVehicle1);
            Saves savedSaves1 = savesRepository.save(saves1);
            Saves saves2 = new Saves(savedUser2, savedVehicle2);
            Saves savedSaves2 = savesRepository.save(saves2);

            // Preload ModelFeatures
            ModelFeatures modelFeatures1 = new ModelFeatures(savedModel1, savedFeature1);
            ModelFeatures savedModelFeatures1 = modelFeaturesRepository.save(modelFeatures1);
            ModelFeatures modelFeatures2 = new ModelFeatures(savedModel2, savedFeature2);
            ModelFeatures savedModelFeatures2 = modelFeaturesRepository.save(modelFeatures2);

            // Preload TrimFeatures
            TrimFeatures trimFeatures1 = new TrimFeatures(savedFeature1, savedTrimLevel1);
            TrimFeatures savedTrimFeatures1 = trimFeaturesRepository.save(trimFeatures1);
            TrimFeatures trimFeatures2 = new TrimFeatures(savedFeature2, savedTrimLevel2);
            TrimFeatures savedTrimFeatures2 = trimFeaturesRepository.save(trimFeatures2);

        };
    }
}
