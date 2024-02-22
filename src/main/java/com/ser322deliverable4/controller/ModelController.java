package com.ser322deliverable4.controller;

import com.ser322deliverable4.dto.AddModelDto;
import com.ser322deliverable4.model.TrimLevel;
import com.ser322deliverable4.repository.ModelRepository;
import com.ser322deliverable4.repository.TrimLevelRepository;
import com.ser322deliverable4.service.model.IModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
public class ModelController {

    private final IModelService modelService;
    private final ModelRepository modelRepository;
    private final TrimLevelRepository trimLevelRepository;
    private final Logger logger = LoggerFactory.getLogger(ModelController.class);

    public ModelController(IModelService modelService,
                           ModelRepository modelRepository,
                           TrimLevelRepository trimLevelRepository) {
        this.modelService = modelService;
        this.modelRepository = modelRepository;
        this.trimLevelRepository = trimLevelRepository;
    }

    @GetMapping("/model-services")
    public String addModelPage(Model model) {
        com.ser322deliverable4.model.Model modell = new com.ser322deliverable4.model.Model();
        model.addAttribute("model", modell);
        List<com.ser322deliverable4.model.Model> modelList = modelRepository.findAll();
        model.addAttribute("modelList", modelList);
        List<TrimLevel> allTrimLevels = trimLevelRepository.findAllTrimLevels();
        model.addAttribute("allTrimLevels", allTrimLevels);
        model.addAttribute("addModelDto", new AddModelDto());
        return "model-services";
    }

    @GetMapping("/edit-model/{modelId}")
    public String editModelPage(@PathVariable Long modelId, Model model, RedirectAttributes redirectAttributes) {
        Optional<com.ser322deliverable4.model.Model> modelById = modelRepository.findModelById(modelId);
        if (modelById.isPresent()) {
            model.addAttribute("editModel", modelById.get());
            return "edit-model";
        }
        redirectAttributes.addFlashAttribute("error", "Cannot find model by model id " + modelId);
        return "redirect:../model-services";
    }

    @PostMapping("/edit-model")
    public String editModel(@ModelAttribute("editModel") com.ser322deliverable4.model.Model editModel,
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

    @PostMapping("/add-model")
    public String addModel(@ModelAttribute("addModelDto") AddModelDto addModelDto,
                           RedirectAttributes redirectAttributes) throws Exception {
        logger.info("RECEIVED NEW MODEL NAME: {}", addModelDto.getName());
        logger.info("RECEIVED NEW MODEL YEAR: {}", addModelDto.getYear());
        logger.info("RECEIVED NEW MODEL STYLE: {}", addModelDto.getStyle());
        logger.info("RECEIVED TRIM LEVEL: {}", addModelDto.getTrimLevelName());
        try {
            Optional<TrimLevel> trimLevelByName = trimLevelRepository.findTrimLevelByName(addModelDto.getTrimLevelName());
            int response = modelRepository.insertNewModel(
                    addModelDto.getName(),
                    addModelDto.getYear(),
                    addModelDto.getStyle(),
                    trimLevelByName.get().getTrimId());
            logger.info("ROWS CHANGED IN DB: {}", response);
            return "redirect:model-services";
        } catch (Exception e) {
            logger.error("UNABLE TO CREATE NEW MODEL ENTRY.");
            String message = "Cannot add model because: " + e.getMessage();
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:model-services";
        }
    }
}
