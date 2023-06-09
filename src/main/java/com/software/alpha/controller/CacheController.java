package com.software.alpha.controller;

import com.software.alpha.model.CachedVideo;
import com.software.alpha.model.GenericResponse;
import com.software.alpha.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @PostMapping("/cache/videos")
    public ResponseEntity<GenericResponse> cacheAllVideos(@RequestBody(required = false) String auth){
        return cacheService.cacheVideos(auth);
    }

    @GetMapping("/cache/videos/all")
    public ResponseEntity<List<CachedVideo>> getCachedVideos(){
        return cacheService.getCachedVideos();
    }
}
