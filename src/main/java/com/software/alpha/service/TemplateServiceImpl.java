package com.software.alpha.service;

import com.software.alpha.model.YoutubeItem;
import com.software.alpha.model.YoutubeResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final String INDEX_TEMPLATE = "index";

    @Autowired
    private MainService mainService;

    @Override
    public String getIndex(Model model) {
        ResponseEntity<YoutubeResults> responseEntity = mainService.getVideos();
        YoutubeResults youtubeResults = responseEntity.getBody();
        if (youtubeResults == null) return INDEX_TEMPLATE;
        YoutubeItem[] youtubeItems = youtubeResults.getItems();
        model.addAttribute("items", youtubeItems);
        return INDEX_TEMPLATE;
    }

}
