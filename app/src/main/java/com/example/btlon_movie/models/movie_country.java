package com.example.btlon_movie.models;

public class movie_country {
    private int ID_Movie;
    private int ID_Country;

    public movie_country(int ID_Movie, int ID_Country) {
        this.ID_Movie = ID_Movie;
        this.ID_Country = ID_Country;
    }

    public int getID_Movie() {
        return ID_Movie;
    }

    public void setID_Movie(int ID_Movie) {
        this.ID_Movie = ID_Movie;
    }

    public int getID_Country() {
        return ID_Country;
    }

    public void setID_Country(int ID_Country) {
        this.ID_Country = ID_Country;
    }
}
