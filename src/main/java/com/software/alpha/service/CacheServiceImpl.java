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

    @Autowired
    private CachedVideoRepository cachedVideoRepository;

    @Override
    public ResponseEntity<GenericResponse> cacheVideos() {

        YoutubeResults results;

        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<YoutubeResults> responseEntity = restTemplate.getForEntity(URL, YoutubeResults.class);
            results = responseEntity.getBody();

            System.out.println("Successfully called youtube service " + URL);

        } catch (Exception ex) {
            System.out.println("error calling youtube service " + URL);
            ex.printStackTrace();
            return createBadRequestResponse(ex.getMessage());
        }

        YoutubeItem[] youtubeItems = results.getItems();

        if (youtubeItems == null) return createBadRequestResponse("youtube service response did not contain items array");

        if (youtubeItems.length < 5) return createBadRequestResponse("5 videos weren't returned from the service. " +
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
            }

            cachedVideo.setTitle(title);
            cachedVideo.setVideoId(videoId);
            cachedVideoRepository.save(cachedVideo);
        }

        return createOkResponse("successfully cached videos");


    }

    @Override
    public ResponseEntity<List<CachedVideo>> getCachedVideos() {
        return ResponseEntity.status(HttpStatus.OK).body(cachedVideoRepository.findAll());
    }

    private ResponseEntity<GenericResponse> createOkResponse(String message) {
        return createGenericResponse(HttpStatus.OK, message);
    }

    private ResponseEntity<GenericResponse> createBadRequestResponse(String message) {
        return createGenericResponse(HttpStatus.BAD_REQUEST, message);
    }

    private ResponseEntity<GenericResponse> createGenericResponse(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus).
                body(new GenericResponse(httpStatus.value(), message));
    }


}
