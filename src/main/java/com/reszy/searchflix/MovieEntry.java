package com.reszy.searchflix;

/**
 * Created by Rafał on 23.04.2017.
 */
public class MovieEntry {
    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String title;
    private String picture;

    public MovieEntry() {

    }

    public MovieEntry(String title, String picture) {
        this.title = title;
        this.picture = picture;
    }
}
