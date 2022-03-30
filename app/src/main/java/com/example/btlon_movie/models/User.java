package com.example.btlon_movie.models;

import android.app.ProgressDialog;
import android.widget.ProgressBar;

import java.util.List;

public class User {
    private String ID;
    private String Account;
    private String Password;
    private String Type;
    private List<IDMovie> MyList;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String ID, String account, String password, String type) {
        this.ID = ID;
        Account = account;
        Password = password;
        Type = type;
    }

    public User(String ID, String account, String type) {
        this.ID = ID;
        Account = account;
        Type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public List<IDMovie> getMyList() {
        return MyList;
    }

    public void setMyList(List<IDMovie> myList) {
        MyList = myList;
    }
    public void addMyList(IDMovie myMovie) {
        MyList.add(myMovie);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Account='" + Account + '\'' +
                ", Password='" + Password + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
