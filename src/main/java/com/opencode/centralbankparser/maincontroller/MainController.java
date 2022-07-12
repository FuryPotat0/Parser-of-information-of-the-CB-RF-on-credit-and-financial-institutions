package com.opencode.centralbankparser.maincontroller;

import com.opencode.centralbankparser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private Parser parser;

    @GetMapping("/")
    public String getMain(){
        LOGGER.info("user go on MainController page");
        return "index";
    }

    @PostMapping("/upload")
    public String upload(Model model, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                model.addAttribute("errorText", "Ok");
                parser.deserializeXml(file);
                return "index";
            } catch (Exception e) {
                model.addAttribute("errorText", "ошибка при чтении, проверьте содержимое файла");
                LOGGER.error("error while unmarshalling");
                LOGGER.error(e.getMessage(), e);
            }
        } else model.addAttribute("errorText", "пустой файл");
        return "index";
    }
}

