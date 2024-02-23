package com.ser322deliverable4.service.save;

import com.ser322deliverable4.model.Saves;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;


public interface ISaveService {

	Saves addSave(Saves save);
	
	int deleteSaveByVin(String vin) throws SQLIntegrityConstraintViolationException;
	
	List<Saves> getAllSaves();


}

