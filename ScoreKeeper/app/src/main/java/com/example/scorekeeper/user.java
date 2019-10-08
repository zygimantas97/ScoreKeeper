package com.example.scorekeeper;

import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("response")
    private String response;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("password")
    private String password;


    public String getResponse() {
        return response;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }


}
