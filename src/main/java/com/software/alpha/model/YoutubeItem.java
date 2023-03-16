package com.software.alpha.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YoutubeItem {

    private YoutubeVideoId id;
    private String etag;
    @JsonProperty("snippet")
    private YoutubeVideo videos;


    public YoutubeVideoId getId() {
        return id;
    }

    public void setId(YoutubeVideoId id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public YoutubeVideo getVideos() {
        return videos;
    }

    public void setVideos(YoutubeVideo videos) {
        this.videos = videos;
    }
}
