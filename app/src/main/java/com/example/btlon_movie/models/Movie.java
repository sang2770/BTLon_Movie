package com.example.btlon_movie.models;

public class Movie {
    private int ID;
    private String title;
    private int CoverPhoto;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private String streamingLink;

    public Movie(int ID, String title, int coverPhoto, String description, int thumbnail, String studio, String rating, String streamingLink) {
        this.ID = ID;
        this.title = title;
        CoverPhoto = coverPhoto;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamingLink = streamingLink;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int  getCoverPhoto() {
        return CoverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        CoverPhoto = coverPhoto;
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

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }
}
