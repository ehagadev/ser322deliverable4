package com.ser322deliverable4.service.manufacturer;

import com.ser322deliverable4.model.Manufacturer;
import com.ser322deliverable4.repository.ManufacturerRepository;
import com.ser322deliverable4.service.user.UserServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public int editManufacturer(Manufacturer editManufacturer) {
        logger.info("AT BEGINNING OF EDIT MANUFACTURER");
        Optional<Manufacturer> byName = manufacturerRepository.findByName(editManufacturer.getName());
        if (byName.isPresent()) {
            Manufacturer manufacturer = byName.get();
            manufacturer.setCountry(editManufacturer.getCountry());
            manufacturerRepository.save(manufacturer);
            logger.info("SUCCESSFULLY EDITED MANUFACTURER {}", manufacturer.getName());
            return 1;
        } else {
            logger.error("MANUFACTURER NOT FOUND BY NAME: {}", editManufacturer.getName());
            return 0;
        }
    }

    @Override
    public Manufacturer getByName(String name) {
        logger.info("ATTEMPTING TO FIND MANUFACTURER BY NAME: {}", name);
        Optional<Manufacturer> byName = manufacturerRepository.findByName(name);
        if (byName.isPresent()) {
            Manufacturer manufacturer = byName.get();
            logger.info("FOUND MANUFACTURER BY NAME: {}", manufacturer.getName());
            return manufacturer;
        } else {
            logger.error("MANUFACTURER NOT FOUND BY NAME: {}", name);
            return null;
        }
    }

    @Override
    public int deleteManufacturer(String manName) throws SQLIntegrityConstraintViolationException {
        Optional<Manufacturer> byName = manufacturerRepository.findByName(manName);
        if (byName.isPresent()) {
            Manufacturer manufacturer = byName.get();
            try {
                int response = manufacturerRepository.deleteByName(manufacturer.getName());
                logger.info("SUCCESSFULLY DELETED MANUFACTURER BY NAME: {}", manufacturer.getName());
                return response;
            } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
                logger.error("UNABLE TO DELETE MANUFACTURER: {}. It has associated references.", manName);
                throw new SQLIntegrityConstraintViolationException("Unable to delete manufacturer. It has associated references", ex);
            }

        } else {
            logger.error("UNABLE TO FIND MANUFACTURER BY NAME: {}", manName);
            return 0;
        }
    }
}
