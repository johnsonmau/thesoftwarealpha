package com.software.alpha.service;

import org.springframework.ui.Model;

public interface TemplateService {

    String getIndex(Model model);

    String getAdmin(Model mode);

}
