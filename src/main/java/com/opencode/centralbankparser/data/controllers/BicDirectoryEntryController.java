package com.opencode.centralbankparser.data.controllers;

import com.opencode.centralbankparser.data.entities.BicDirectoryEntryEntity;
import com.opencode.centralbankparser.data.services.BicDirectoryEntryService;
import com.opencode.centralbankparser.data.services.Ed807Service;
import com.opencode.centralbankparser.references.services.ChangeTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BicDirectoryEntryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BicDirectoryEntryController.class);

    @Autowired
    private BicDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    private ChangeTypeService changeTypeService;
    @Autowired
    private Ed807Service ed807Service;

    @GetMapping("/bic-rectory-entry")
    public String getAll(@RequestParam(value = "errorText", required = false) String errorText, Model model) {
        LOGGER.info("User go on BicDirectoryEntryController page");
        if (errorText != null)
            model.addAttribute("errorText", errorText);
        return "dataTemplates/common/ed807";
    }

    @PostMapping("/bic-rectory-entry/add")
    public ModelAndView addEntity(@RequestParam(required = false) Map<String, String> params,
                                  ModelMap model) {
        try {
            BicDirectoryEntryEntity entity = new BicDirectoryEntryEntity();
            entity.setBic(params.get("bic"));
            entity.setChangeType(changeTypeService.findById(Long.parseLong(params.get("changeTypeEntity"))));
            entity.setEd807(ed807Service.findById(Long.parseLong(params.get("ed807"))));
            bicDirectoryEntryService.save(entity);
            if (bicDirectoryEntryService.findById(entity.getIdBicDirectoryEntry()) == null)
                throw new RuntimeException("BicDirectoryEntry wasn't added");
        } catch (Exception e) {
            model.addAttribute("errorText", "Введены неверные данные");
            LOGGER.warn("BicDirectoryEntry wasn't added");
            LOGGER.warn(e.getMessage());
        }
        return new ModelAndView("redirect:/ed807/" + params.get("ed807"), model);
    }
}

