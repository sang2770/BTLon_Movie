package com.example.btlon_movie;

public class Slide {
    private int image;
    private String Title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Slide(int image, String title) {
        this.image = image;
        Title = title;
    }


}
