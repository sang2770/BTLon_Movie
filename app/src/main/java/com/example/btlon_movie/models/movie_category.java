package com.example.btlon_movie.models;

public class movie_category {
    private int ID_Movie;
    private int ID_category;

    public movie_category(int ID_Movie, int ID_category) {
        this.ID_Movie = ID_Movie;
        this.ID_category = ID_category;
    }

    public int getID_Movie() {
        return ID_Movie;
    }

    public void setID_Movie(int ID_Movie) {
        this.ID_Movie = ID_Movie;
    }

    public int getID_category() {
        return ID_category;
    }

    public void setID_category(int ID_category) {
        this.ID_category = ID_category;
    }
}
