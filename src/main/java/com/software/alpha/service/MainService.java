package com.software.alpha.service;

import com.software.alpha.model.YoutubeResults;
import org.springframework.http.ResponseEntity;

public interface MainService {

    String getIndex();
    ResponseEntity<YoutubeResults> getVideos();

}
