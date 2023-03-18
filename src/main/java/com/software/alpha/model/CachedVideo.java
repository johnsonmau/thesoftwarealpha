package com.software.alpha.model;

import javax.persistence.*;

@Entity
public class CachedVideo {

    public CachedVideo() {
    }

    public CachedVideo(String videoId, String title) {
        this.videoId = videoId;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String videoId;

    @Column
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
