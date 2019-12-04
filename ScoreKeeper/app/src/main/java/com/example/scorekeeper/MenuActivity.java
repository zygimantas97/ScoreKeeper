package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.*;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.JsonArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    Game[] games;
    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            Game game = (Game)view.getTag();
            intent.putExtra("gameObject", game);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        games = getGames();



    }

    private Button createButton(){
        final Button newButton = new Button(this);
        return newButton;
    }

    private Game[] getGames(){

        final List<Game> gamesList = new ArrayList<>();

        String id_string = MainActivity.prefConfig.readID();
        MainActivity.prefConfig.displayMessage(id_string);

        int id = Integer.parseInt(id_string);


        Call<GamesContainer> call = MainActivity.api_interface.getGames(id);
        call.enqueue(new Callback<GamesContainer>() {
            @Override
            public void onResponse(Call<GamesContainer> call, Response<GamesContainer> response) {
                if (response.body().getResponse().equals("ok")){
                   List<Game> games_data = new ArrayList<>();
                  for(Game single_game : response.body().getGames()) {
                      games_data.add(single_game);
                  }


                    for(int i = 0; i < games_data.size(); i++){
                        Button newButton = createButton();

                        String buttonString = games_data.get(i).getDate() + " " + games_data.get(i).getTime() + " " + games_data.get(i).getPlayer1FullName() + " VS " + games_data.get(i).getPlayer2FullName();
                        newButton.setText(buttonString);
                        newButton.setId(i+1);
                        newButton.setTag(games_data.get(i));
                        newButton.setOnClickListener(buttonClick);
                        newButton.setBackground(getDrawable(R.drawable.button_style));
                        newButton.setPadding(15,15,15,15);
                        newButton.setTextSize(20);
                        newButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                        newButton.setBottom(6);
                        newButton.setTop(15);
                        LinearLayout layoutOfButtons = findViewById(R.id.gameListLayout);
                        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                        layoutOfButtons.addView(newButton, layoutParams);

                    }

                }
                else if (response.body().getResponse().equals("failed")){
                    MainActivity.prefConfig.displayMessage("No games for you found");
                }
            }


            @Override
            public void onFailure(Call<GamesContainer> call, Throwable t) {
                MainActivity.prefConfig.displayMessage(t.getMessage());
            }
        });


        Game[] games_data = new Game[gamesList.size()];


        for(int i=0; i < games_data.length; i++){
            games_data[i] = gamesList.get(i);
        }


        Game[] generatedGames = new Game[3];
        generatedGames[0] = new Game(1, 4, "Jonas Jonaitis", "Petras Petraitis", 3, 2, "2019-12-12", "12:00", 1);
        generatedGames[1] = new Game(2, 4, "Jonas Jonaitis", "Simas Simaitis", 3, 2, "2019-12-12", "12:30", 1);
        generatedGames[2] = new Game(3, 4, "Petras Petraitis", "Simas Simaitis", 3, 2, "2019-12-12", "13:00", 1);

        Integer intVal = new Integer(games_data.length);
        String stringVal = intVal.toString();
        MainActivity.prefConfig.displayMessage(stringVal);
        return games_data;
    }
}
