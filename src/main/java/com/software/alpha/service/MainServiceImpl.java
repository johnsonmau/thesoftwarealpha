package com.software.alpha.service;

import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Override
    public String getIndex() {
        return "Application under construction - MJ";
    }

}
