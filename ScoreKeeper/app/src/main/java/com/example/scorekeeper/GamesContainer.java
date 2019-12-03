package com.example.scorekeeper;

import android.inputmethodservice.Keyboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;


import com.google.gson.annotations.SerializedName;

public class GamesContainer {
    @SerializedName("response")
    private String response;

    @SerializedName("games")
    private List<Game> games;

    public String getResponse() {
        return response;
    }

    public List<Game>  getGames() {
        return games;
    }

}
