package com.opencode.centralbankparser.controllers;

import com.opencode.centralbankparser.Parser;
import com.opencode.centralbankparser.data.daos.Ed807Dao;
import com.opencode.centralbankparser.references.FillReferences;
import com.opencode.centralbankparser.references.services.InfoTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private FillReferences fillReferences;

    @Autowired
    private Ed807Dao ed807Dao;

    @Autowired
    private Parser parser;

    @Autowired
    private InfoTypeCodeService infoTypeCodeService;

    @GetMapping("/")
    public String getMain(){
//        for (InfoTypeCodeEntity entity: infoTypeCodeService.getAll()){
//            System.out.println(entity.getCode());
//        }
//        System.out.println(infoTypeCodeService.findByCode("FIRR").get().getName());
//        System.out.println(infoTypeCodeDao.findByCode("FEAR").get().getName());
        parser.deserializeXml();
        return "kek";
    }
}

