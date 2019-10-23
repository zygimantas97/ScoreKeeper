package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        game = (Game)intent.getSerializableExtra("gameObject");
        TextView player1TextView = findViewById(R.id.player1TextView);
        player1TextView.setText(String.valueOf(game.getPlayer1ID()));
        TextView player2TextView = findViewById(R.id.player2TextView);
        player2TextView.setText(String.valueOf(game.getPlayer2ID()));

    }

    public void onIncreaseResult1Button1Click(View view){
        Button increase1Btn1 = findViewById(R.id.increaseResult1Button1);
        Button increase1Btn2 = findViewById(R.id.increaseResult1Button2);
        Button increase2Btn1 = findViewById(R.id.increaseResult2Button1);
        Button increase2Btn2 = findViewById(R.id.increaseResult2Button2);
        increase1Btn1.setText("+1");
        increase1Btn2.setText("+1");
        increase2Btn1.setVisibility(View.VISIBLE);
        increase2Btn2.setVisibility(View.VISIBLE);

    }
    public void onIncreaseResult1Button2Click(View view){
        Button increase1Btn1 = findViewById(R.id.increaseResult1Button1);
        Button increase1Btn2 = findViewById(R.id.increaseResult1Button2);
        Button increase2Btn1 = findViewById(R.id.increaseResult2Button1);
        Button increase2Btn2 = findViewById(R.id.increaseResult2Button2);
        increase1Btn1.setText("+1");
        increase1Btn2.setText("+1");
        increase2Btn1.setVisibility(View.VISIBLE);
        increase2Btn2.setVisibility(View.VISIBLE);
    }
    public void onIncreaseResult2Button1Click(View view){
        // add two points to player1
    }
    public void onIncreaseResult2Button2Click(View view){
        // add two points to player2
    }


}
