package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.Saves;
import com.ser322deliverable4.service.save.ISaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class SaveController {

    private final ISaveService saveService;
    private final Logger logger = LoggerFactory.getLogger(SaveController.class);

    public SaveController(ISaveService saveService) {
        this.saveService = saveService;
    }

}
