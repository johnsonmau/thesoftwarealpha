package com.software.alpha.service;

import com.software.alpha.model.CachedVideo;
import com.software.alpha.model.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CacheService {

    ResponseEntity<GenericResponse> cacheVideos();
    ResponseEntity<List<CachedVideo>> getCachedVideos();

}
