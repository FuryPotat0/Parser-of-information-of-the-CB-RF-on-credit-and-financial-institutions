package com.opencode.centralbankparser.controllers;

import com.opencode.centralbankparser.FillReferences;
import com.opencode.centralbankparser.daos.Ed807Dao;
import com.opencode.centralbankparser.daos.InfoTypeCodeDao;
import com.opencode.centralbankparser.entities.InfoTypeCodeEntity;
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

        return "kek";
    }
}

