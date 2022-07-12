package com.opencode.centralbankparser.data.controllers;

import com.opencode.centralbankparser.data.entities.Ed807Entity;
import com.opencode.centralbankparser.data.services.BicDirectoryEntryService;
import com.opencode.centralbankparser.data.services.Ed807Service;
import com.opencode.centralbankparser.references.services.ChangeTypeService;
import com.opencode.centralbankparser.references.services.CreationReasonService;
import com.opencode.centralbankparser.references.services.InfoTypeCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class Ed807Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ed807Controller.class);

    @Autowired
    private Ed807Service ed807Service;
    @Autowired
    private CreationReasonService creationReasonService;
    @Autowired
    private InfoTypeCodeService infoTypeCodeService;
    @Autowired
    private BicDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    private ChangeTypeService changeTypeService;

    @GetMapping("/ed807")
    public String getAll(@RequestParam(value = "errorText", required = false) String errorText, Model model) {
        LOGGER.info("User go on Ed807Controller page");
        List<Ed807Entity> ed807List = ed807Service.getAll();

        if (errorText != null)
            model.addAttribute("errorText", errorText);
        model.addAttribute("ed807List", ed807List);
        model.addAttribute("creationReasonList", creationReasonService.getAll());
        model.addAttribute("infoTypeCodeList", infoTypeCodeService.getAll());
        return "dataTemplates/common/ed807";
    }

    @PostMapping("/ed807/add")
    public ModelAndView addEntity(@RequestParam(required = false) Map<String, String> params,
                                  ModelMap model) {
        try {
            Ed807Entity entity = new Ed807Entity();
            entity.setEdNo(params.get("edNo"));
            entity.setEdAuthor(params.get("edAuthor"));
            entity.setEdReceiver(params.get("edReceiver"));
            entity.setCreationReasonEntity(creationReasonService.findById(Long.parseLong(params.get("creationReasonEntity"))));
            entity.setInfoTypeCodeEntity(infoTypeCodeService.findById(Long.parseLong(params.get("infoTypeCodeEntity"))));
            entity.setDirectoryVersion(params.get("directoryVersion"));

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = date.parse(params.get("edDate"));
            entity.setEdDate(new Timestamp(parsedDate.getTime()));
            parsedDate = date.parse(params.get("creationDateTime"));
            entity.setCreationDateTime(new Timestamp(parsedDate.getTime()));
            parsedDate = date.parse(params.get("businessDay"));
            entity.setBusinessDay(new Timestamp(parsedDate.getTime()));

            ed807Service.save(entity);
            if (ed807Service.findById(entity.getIdEd()) == null)
                throw new RuntimeException("Ed807 wasn't added");
            return new ModelAndView("redirect:/ed807", model);
        } catch (Exception e) {
            model.addAttribute("errorText", "Введены неверные данные");
            LOGGER.warn("Ed807 wasn't added");
            LOGGER.warn(e.getMessage());
            return new ModelAndView("redirect:/ed807", model);
        }
    }

    @RequestMapping("/ed807/delete/{id}")
    public ModelAndView deleteEntity(@PathVariable Long id, ModelMap model) {
        if (ed807Service.findById(id) != null) {
            try {
                ed807Service.delete(id);
                LOGGER.info("Ed807 with id={} was deleted", id);
            } catch (DataIntegrityViolationException e) {
                LOGGER.info("Ed807 with id={} wasn't deleted", id);
                model.addAttribute("errorText",
                        "Невозможно удалить объект, удалите все связанные элементы");
            }
        } else LOGGER.warn("Ed807 with id={} don't exist", id);
        return new ModelAndView("redirect:/ed807", model);
    }

    @GetMapping("/ed807/{id}")
    public String getEd807(@PathVariable(value = "id") Long id, Model model){
        Ed807Entity entity = ed807Service.findById(id);
        if (entity != null){
            model.addAttribute("ed807", entity);
            model.addAttribute("changeTypeList", changeTypeService.getAll());
            model.addAttribute("bicList", bicDirectoryEntryService.findByEd807Id(id));
        }
        return "dataTemplates/common/ed807Individual";
    }

    @GetMapping("/ed807/edit/{id}")
    public String editEntityGet(@PathVariable(value = "id") Long id, Model model) {
        Ed807Entity entity = ed807Service.findById(id);
        LOGGER.info("User want visit Ed807 with id={}", id);
        if (entity != null) {
            model.addAttribute("ed807", entity);
            model.addAttribute("creationReasonList", creationReasonService.getAll());
            model.addAttribute("infoTypeCodeList", infoTypeCodeService.getAll());
            LOGGER.info("Ed807 with id={} will be edited", id);
            return "dataTemplates/edit/ed807-edit";
        } else {
            LOGGER.warn("No Ed807 with id={}", id);
            return "redirect:/ed807";
        }
    }

    @PostMapping("/ed807/edit/{id}")
    public ModelAndView editEntityPost(@PathVariable(value = "id") Long id,
                                       @RequestParam(required = false) Map<String, String> params,
                                  ModelMap model) {
        try {
            Ed807Entity entity = ed807Service.findById(id);
            entity.setEdNo(params.get("edNo"));

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = date.parse(params.get("edDate"));
            entity.setEdDate(new Timestamp(parsedDate.getTime()));
            parsedDate = date.parse(params.get("creationDateTime"));
            entity.setCreationDateTime(new Timestamp(parsedDate.getTime()));
            parsedDate = date.parse(params.get("businessDay"));
            entity.setBusinessDay(new Timestamp(parsedDate.getTime()));

            entity.setEdAuthor(params.get("edAuthor"));
            entity.setEdReceiver(params.get("edReceiver"));
            entity.setCreationReasonEntity(creationReasonService.findById(Long.parseLong(params.get("creationReasonEntity"))));
            entity.setInfoTypeCodeEntity(infoTypeCodeService.findById(Long.parseLong(params.get("infoTypeCodeEntity"))));
            entity.setDirectoryVersion(params.get("directoryVersion"));

            ed807Service.update(entity);
            if (ed807Service.findById(entity.getIdEd()) == null)
                throw new RuntimeException("Ed807 wasn't edited");
            return new ModelAndView("redirect:/ed807", model);
        } catch (Exception e) {
            model.addAttribute("errorText", "Введены неверные данные");
            LOGGER.warn("Ed807 wasn't edited");
            LOGGER.warn(e.getMessage());
            return new ModelAndView("redirect:/ed807/edit/" + id, model);
        }
    }
}

