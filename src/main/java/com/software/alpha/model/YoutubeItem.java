package com.software.alpha.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YoutubeItem {

    private YoutubeVideoId id;
    private String etag;
    @JsonProperty("snippet")
    private YoutubeVideo video;


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

    public YoutubeVideo getVideo() {
        return video;
    }

    public void setVideo(YoutubeVideo video) {
        this.video = video;
    }
}
