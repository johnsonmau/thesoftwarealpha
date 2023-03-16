package com.software.alpha.controller;

import com.software.alpha.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @Autowired
    TemplateService templateService;

    @GetMapping("/")
    public String getIndex(){
        return templateService.getIndex();
    }

}
