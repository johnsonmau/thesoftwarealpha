package com.software.alpha.service;

import com.software.alpha.model.CachedVideo;
import com.software.alpha.repository.CachedVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private CacheService cacheService;

    private final String INDEX_TEMPLATE = "index";

    @Override
    public String getIndex(Model model) {
        List<CachedVideo> cachedVideos = cacheService.getCachedVideos().getBody();
        if (cachedVideos == null) return INDEX_TEMPLATE;
        model.addAttribute("cachedVideos", cachedVideos);
        return INDEX_TEMPLATE;
    }

}
