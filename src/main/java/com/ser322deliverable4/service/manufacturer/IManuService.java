package com.ser322deliverable4.service.manufacturer;

import com.ser322deliverable4.model.Manufacturer;

import java.util.List;

public interface IManuService {

    Manufacturer addManufacturer(Manufacturer manufacturer);

    List<Manufacturer> getAll();
}
