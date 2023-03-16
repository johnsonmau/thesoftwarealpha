package com.software.alpha.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YoutubeThumbnail {
    @JsonProperty("default")
    private GenericThumbnail defaultThumbnail;
    @JsonProperty("medium")
    private GenericThumbnail mediumThumbnail;
    @JsonProperty("high")
    private GenericThumbnail highThumbnail;

    public GenericThumbnail getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public void setDefaultThumbnail(GenericThumbnail defaultThumbnail) {
        this.defaultThumbnail = defaultThumbnail;
    }

    public GenericThumbnail getMediumThumbnail() {
        return mediumThumbnail;
    }

    public void setMediumThumbnail(GenericThumbnail mediumThumbnail) {
        this.mediumThumbnail = mediumThumbnail;
    }

    public GenericThumbnail getHighThumbnail() {
        return highThumbnail;
    }

    public void setHighThumbnail(GenericThumbnail highThumbnail) {
        this.highThumbnail = highThumbnail;
    }
}
