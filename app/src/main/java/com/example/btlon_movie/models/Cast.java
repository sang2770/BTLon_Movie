package com.example.btlon_movie.models;

import android.widget.ImageView;

public class Cast {
    private  String Name;
    private int img_Link;

    public Cast(String name, int img_Link) {
        Name = name;
        this.img_Link = img_Link;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImg_Link() {
        return img_Link;
    }

    public void setImg_Link(int img_Link) {

        this.img_Link = img_Link;
    }
}
