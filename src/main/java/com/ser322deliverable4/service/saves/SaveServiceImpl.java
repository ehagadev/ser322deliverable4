package com.ser322deliverable4.service.save;

import com.ser322deliverable4.model.Saves;
import com.ser322deliverable4.repository.SavesRepository;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveServiceImpl implements ISaveService {

    private final SavesRepository savesRepository;

    private final Logger logger = LoggerFactory.getLogger(SaveServiceImpl.class);

    public SaveServiceImpl(SavesRepository saveRepository) {
        this.savesRepository = saveRepository;
    }


    @Override
    public Saves addSave(Saves save) {
        savesRepository.addNewSave(
                save.getUser(),
                save.getVehicle()
        );
        logger.info("SUCCESSFULLY ADDED NEW SAVE {} INTO THE DB", save.getId().getUserId());
        return save;
    }
    

    @Override
    public int deleteSaveByVin(String vin) throws SQLIntegrityConstraintViolationException {
        List<Saves> byVin = savesRepository.findSavesByVehicleVin(vin);
        if (byVin.size() == 1) {
            try {
                int response = savesRepository.deleteSaveByVehicleVin(vin);
                logger.info("SUCCESSFULLY DELETED SAVE BY VIN: {}", vin);
                return response;
            } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
                logger.error("UNABLE TO DELETE VIN: {}. It has associated references.", vin);
                throw new SQLIntegrityConstraintViolationException("Unable to delete save. It has associated references", ex);
            }
        } else {
            logger.error("UNABLE TO FIND SAVE BY VIN: {}", vin);
            return 0;
        }
    }


}
