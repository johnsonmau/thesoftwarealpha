package com.software.alpha.controller;

import com.software.alpha.model.YoutubeResults;
import com.software.alpha.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

/*    @GetMapping("/videos")
    public @ResponseBody ResponseEntity<YoutubeResults> getRecentVideos(){
        return mainService.getVideos();
    }*/
}
