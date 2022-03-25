package com.example.btlon_movie.models;

public class Movie {
    private int ID;
    private String Name;
    private String Image;
    private String Description;
    private String thumbnail;
    private String language;
    private Category Category;
    private  Country Country;
    private String rating;
    private String Link;
    private int Year;

    public Movie() {
    }

    public Movie(int ID, String name, String image, String description, String thumbnail, String language, Category category, Country country, String rating, String link, int year) {
        this.ID = ID;
        Name = name;
        Image = image;
        Description = description;
        this.thumbnail = thumbnail;
        this.language = language;
        this.Category = category;
        this.Country = country;
        this.rating = rating;
        Link = link;
        Year = year;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Integer.parseInt(Image);
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Integer.parseInt(thumbnail);
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        this.Category = category;
    }

    public Country getCountry() {
        return Country;
    }

    public void setCountry(Country country) {
        this.Country = country;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }
    /*public Movie(int ID, String title, int coverPhoto, String description, int thumbnail, String studio, String rating, String streamingLink) {
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
    }*/
}
