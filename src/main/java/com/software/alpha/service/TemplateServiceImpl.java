package com.software.alpha.service;

import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final String INDEX_TEMPLATE = "index";

    @Override
    public String getIndex() {
        return INDEX_TEMPLATE;
    }

}
