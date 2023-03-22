package com.software.alpha.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    @Column(name="post_id")
    private long id;

    @Column
    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date lastUpdated;

    @Column
    private String photoName;

    @Column
    @Lob
    private byte[] photoData;

    public BlogPost() {
    }

    public BlogPost(String title, String body, Date created,
                    Date lastUpdated, String photoName, byte[] photoData) {
        this.title = title;
        this.body = body;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.photoName = photoName;
        this.photoData = photoData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}
