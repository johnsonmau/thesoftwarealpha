package com.software.alpha.controller;

import com.software.alpha.model.BlogPost;
import com.software.alpha.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TemplateController {

    @Autowired
    TemplateService templateService;

    @GetMapping("/")
    public String getIndex(Model model){
        return templateService.getIndex(model);
    }

    @GetMapping("/admin")
    public String getAdmin(Model model){
        return templateService.getAdmin(model);
    }

    @ModelAttribute("post")
    public BlogPost blogPost(){
        return new BlogPost();
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "login";
    }

}
