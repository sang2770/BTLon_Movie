package com.example.btlon_movie.models;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.btlon_movie.ui.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int ID;
    private String Name;
    private String Image;
    private String Description;
    private String thumbnail;
    private String language;
    private List<Category> category=new ArrayList<>();
    private  List<Country> country=new ArrayList<>();
    private String rating;
    private String Link;
    private int Year;


    public Movie() {
    }

    public Movie(int ID, String name, String image, String description, String thumbnail, String language, List<Category> category, List<Country> country, String rating, String link, int year) {
        this.ID = ID;
        Name = name;
        Image = image;
        Description = description;
        this.thumbnail = thumbnail;
        this.language = language;
        this.category=category;
        this.country=country;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
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

    public String getThumbnail() {
        return thumbnail;
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

    public List<Category> getCategory() {
        return category;
    }
    public String getstrCategory() {
        String theloai="Category :";
        for(int i=0;i<category.size();i++){
            theloai = theloai+" "+category.get(i).getName();
        }
        return theloai;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Country> getCountry() {
        return country;
    }
    public String getstrCountry() {
        String Quocgia="Country :";
        for(int i=0;i<country.size();i++){
            Quocgia = Quocgia+" "+country.get(i).getName();
        }
        return Quocgia;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
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
