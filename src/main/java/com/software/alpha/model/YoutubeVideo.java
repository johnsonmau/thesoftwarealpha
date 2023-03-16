package com.software.alpha.model;

public class YoutubeVideo {

    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private YoutubeThumbnail thumbnails;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public YoutubeThumbnail getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(YoutubeThumbnail thumbnails) {
        this.thumbnails = thumbnails;
    }
}
