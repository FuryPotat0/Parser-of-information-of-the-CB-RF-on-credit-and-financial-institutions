package com.opencode.centralbankparser.controllers;

import com.opencode.centralbankparser.references.FillReferences;
import com.opencode.centralbankparser.data.daos.Ed807Dao;
import com.opencode.centralbankparser.references.daos.InfoTypeCodeDao;
import com.opencode.centralbankparser.references.entities.InfoTypeCodeEntity;
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
    private InfoTypeCodeDao infoTypeCodeDao;

    @GetMapping("/")
    public String getMain(){
        for (InfoTypeCodeEntity entity: infoTypeCodeDao.getAll()){
            System.out.println(entity.getCode());
        }
        System.out.println(infoTypeCodeDao.findByCode("FIRR").get().getName());
//        System.out.println(infoTypeCodeDao.findByCode("FEAR").get().getName());
        return "kek";
    }
}

