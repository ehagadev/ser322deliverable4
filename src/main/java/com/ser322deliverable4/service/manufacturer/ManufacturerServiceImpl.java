package com.ser322deliverable4.service.manufacturer;

import com.ser322deliverable4.model.Manufacturer;
import com.ser322deliverable4.repository.ManufacturerRepository;
import com.ser322deliverable4.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements IManuService {

    private final ManufacturerRepository manufacturerRepository;
    private final Logger logger = LoggerFactory.getLogger(ManufacturerServiceImpl.class);


    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.insertNewManufacturer(
                manufacturer.getName(),
                manufacturer.getCountry()
        );
        logger.info("SUCCESSFULLY ADDED NEW MANUFACTURER {} INTO THE DB", manufacturer.getName());
        return manufacturer;
    }

    @Override
    public List<Manufacturer> getAll() {
        logger.info("FETCHING ALL MANUFACTURERS");
        return manufacturerRepository.findAll();
    }
}
