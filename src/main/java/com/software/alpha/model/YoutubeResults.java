package com.software.alpha.model;

public class YoutubeResults {

    private String etag;
    private YoutubeItem[] items;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public YoutubeItem[] getItems() {
        return items;
    }

    public void setItems(YoutubeItem[] items) {
        this.items = items;
    }
}
