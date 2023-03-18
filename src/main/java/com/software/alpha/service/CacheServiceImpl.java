package com.software.alpha.service;

import com.software.alpha.model.*;
import com.software.alpha.repository.CachedVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    private final String API_KEY = System.getenv("yt_api_key");
    private final String CHANNEL_ID = "UC0kEXeqojM8RqUCejco4_jg";
    private final String PART = "snippet";
    private final String ORDER = "date";
    private final String URL = "https://www.googleapis.com/youtube/v3/search?key=" + API_KEY +
            "&channelId=" + CHANNEL_ID + "&part=" + PART + "&order=" + ORDER;
    private final String CACHE_AUTH = System.getenv("cache_auth");

    @Autowired
    private CachedVideoRepository cachedVideoRepository;

    @Override
    public ResponseEntity<GenericResponse> cacheVideos(String auth) {

        if (auth == null) return createGenericResponse(HttpStatus.UNAUTHORIZED,
                "must provide authentication parameter");
        if (auth.trim() == "") return createGenericResponse(HttpStatus.UNAUTHORIZED,
                "authentication parameter cannot be an empty string");

        if (auth.equals(CACHE_AUTH) == false) return createGenericResponse(HttpStatus.UNAUTHORIZED,
                "invalid authentication string");

        YoutubeResults results;

        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<YoutubeResults> responseEntity = restTemplate.getForEntity(URL, YoutubeResults.class);
            results = responseEntity.getBody();

            System.out.println("Successfully called youtube service " + URL);

        } catch (Exception ex) {
            System.out.println("error calling youtube service " + URL);
            ex.printStackTrace();
            return createGenericResponse(HttpStatus.BAD_REQUEST,ex.getMessage());
        }

        YoutubeItem[] youtubeItems = results.getItems();

        if (youtubeItems == null) return createGenericResponse(HttpStatus.BAD_REQUEST,"youtube service response did not contain items array");

        if (youtubeItems.length < 5) return createGenericResponse(HttpStatus.BAD_REQUEST, "5 videos weren't returned from the service. " +
                "Unable to update db");


        for (int i = 1; i <= 5; i++) {
            YoutubeItem youtubeItem = youtubeItems[i - 1];
            YoutubeVideoId youtubeVideoId = youtubeItem.getId();
            YoutubeVideo youtubeVideo = youtubeItem.getVideo();
            String title = youtubeVideo.getTitle();
            String videoId = youtubeVideoId.getVideoId();
            CachedVideo cachedVideo;

            try {
                cachedVideo = cachedVideoRepository.findById(Long.valueOf(i)).orElseThrow(EntityNotFoundException::new);
                System.out.println("successfully updated cached video with id ["+i+"].");
            } catch (EntityNotFoundException ex) {
                System.out.println("cached video with id ["+i+"] not found. creating new cached video");
                cachedVideo = new CachedVideo();
                cachedVideo.setId(i);
            }

            cachedVideo.setTitle(title);
            cachedVideo.setVideoId(videoId);
            cachedVideoRepository.save(cachedVideo);
        }

        return createGenericResponse(HttpStatus.OK, "successfully cached videos");


    }

    @Override
    public ResponseEntity<List<CachedVideo>> getCachedVideos() {
        return ResponseEntity.status(HttpStatus.OK).body(cachedVideoRepository.findAll());
    }

    private ResponseEntity<GenericResponse> createGenericResponse(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus).
                body(new GenericResponse(httpStatus.value(), message));
    }


}
