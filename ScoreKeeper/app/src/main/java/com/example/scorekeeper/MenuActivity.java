package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    int countOfGames = 5;
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

        for(int i = 0; i < games.length; i++){
            final Button newButton = new Button(this);
            newButton.setText("Button" + (i+1));
            newButton.setId(i+1);
            newButton.setTag(games[i]);
            newButton.setOnClickListener(buttonClick);
            newButton.setBackground(getDrawable(R.drawable.button_style));
            newButton.setPadding(15,15,15,15);
            newButton.setTextSize(20);
            newButton.setTextColor(getResources().getColor(R.color.yellow));

            newButton.setBottom(6);
            newButton.setTop(15);
            LinearLayout layoutOfButtons = findViewById(R.id.gameListLayout);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            layoutOfButtons.addView(newButton, layoutParams);

        }


    }


    private Game[] getGames(){
        Game[] generatedGames = new Game[countOfGames];
        for(int i = 0; i < countOfGames; i++){
            generatedGames[i] = new Game(i);
        }
        return generatedGames;
    }
}
