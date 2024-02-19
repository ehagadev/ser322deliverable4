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
                                         ModelRepository modelRepository) {
        return args -> {
            // Preload Users
            User user1 = new User("secretWindowsFan@apple.com", "'Steve'", "'Jobs'");
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
            Model model1 = new Model("Civic", "2023", "Sedan", savedMan1, savedTrimLevel1);
            Model savedModel1 = modelRepository.save(model1);
            Model model2 = new Model("Silverado", "2022", "Pickup", savedMan2, savedTrimLevel2);
            Model savedModel2 = modelRepository.save(model2);
        };
    }
}
