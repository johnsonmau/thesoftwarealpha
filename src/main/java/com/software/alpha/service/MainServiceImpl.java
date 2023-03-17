package com.software.alpha.service;

import com.software.alpha.model.YoutubeResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MainServiceImpl implements MainService {

    @Override
    public String getIndex() {
        return "Application under construction - MJ";
    }

    @Override
    public ResponseEntity<YoutubeResults> getVideos() {

        String apiKey = "";//"AIzaSyA7fDs3bTlK8ztYwahJQ1LKZh-FgRq193Y";
        String channelId = "UC0kEXeqojM8RqUCejco4_jg";
        String part = "snippet";
        String order = "date";

        String url = "https://www.googleapis.com/youtube/v3/search?key="+apiKey+
                "&channelId="+channelId+"&part="+part+"&order="+order;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<YoutubeResults> responseEntity = restTemplate.getForEntity(url, YoutubeResults.class);
            YoutubeResults results = responseEntity.getBody();
            return ResponseEntity.status(HttpStatus.OK).body(results);
        } catch (Exception ex){
            System.out.println("cannot connect to youtube service.");
            System.out.println(url);
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

    }

}
