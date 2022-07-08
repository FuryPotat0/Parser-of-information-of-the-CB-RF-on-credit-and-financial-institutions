package com.opencode.centralbankparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Parser {
    @EventListener(ApplicationReadyEvent.class)
    public void deserializeXml() {
        try {
            File file = new File("test-data.xml");
            XmlMapper xmlMapper = new XmlMapper();
//            Ed807Entity ed807 = xmlMapper.readValue(file, Ed807Entity.class);
            JsonNode root = xmlMapper.readTree(file);
            System.out.println(root.get("EDNo").asText());
//            System.out.println(root.get("PartInfo"));
            System.out.println(root.get("BICDirectoryEntry").get("BIC").asText());
//            System.out.println(ed807.getEdNo());
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}

