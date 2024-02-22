package com.ser322deliverable4.controller;

import com.ser322deliverable4.repository.ModelRepository;
import com.ser322deliverable4.service.model.IModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
public class ModelController {

    private final IModelService modelService;
    private final ModelRepository modelRepository;
    private final Logger logger = LoggerFactory.getLogger(ModelController.class);

    public ModelController(IModelService modelService,
                           ModelRepository modelRepository) {
        this.modelService = modelService;
        this.modelRepository = modelRepository;
    }

    @GetMapping("/model-services")
    public String addUserPage(Model model) {
        com.ser322deliverable4.model.Model modell = new com.ser322deliverable4.model.Model();
        model.addAttribute("model", modell);
        List<com.ser322deliverable4.model.Model> modelList = modelRepository.findAll();
        model.addAttribute("modelList", modelList);
        return "model-services";
    }

    @GetMapping("/edit-model/{modelId}")
    public String editUserPage(@PathVariable Long modelId, Model model, RedirectAttributes redirectAttributes) {
        Optional<com.ser322deliverable4.model.Model> modelById = modelRepository.findModelById(modelId);
        if (modelById.isPresent()) {
            model.addAttribute("editModel", modelById.get());
            return "edit-model";
        }
        redirectAttributes.addFlashAttribute("error", "Cannot find model by model id " + modelId);
        return "redirect:../model-services";
    }

    @PostMapping("/edit-model")
    public String editUser(@ModelAttribute("editModel") com.ser322deliverable4.model.Model editModel,
                           RedirectAttributes redirectAttributes)
            throws Exception {
        logger.info("EDITING MODEL BY MODEL ID: {}", editModel.getModelId());
        try {
            int response = modelService.editModel(editModel);
            logger.info("ROWS CHANGED IN DB: {}", response);
            return "redirect:model-services";
        } catch (Exception e) {
            String message = "Cannot edit model" + e.getMessage();
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:model-services";
        }

    }

    @GetMapping("/delete-model/{modelId}")
    public String deleteModel(@PathVariable Long modelId, RedirectAttributes redirectAttributes) {
        logger.info("DELETING MODEL BY ID: {}", modelId);
        try {
            int response = modelService.deleteModel(modelId);
            logger.info("ROWS CHANGED IN DB: {}", response);
            return "redirect:../model-services";
        } catch (SQLIntegrityConstraintViolationException ex) {
            logger.error("SQL INTEGRITY CONSTRAINT VIOLATION EXCEPTION");
            redirectAttributes.addFlashAttribute("error", "Cannot delete model due to foreign key constraint.");
            return "redirect:../model-services";
        }
    }
}
