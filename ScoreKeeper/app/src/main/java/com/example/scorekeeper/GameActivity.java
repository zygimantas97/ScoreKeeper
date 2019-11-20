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
        player1TextView.setText(game.getPlayer1FullName());
        TextView player2TextView = findViewById(R.id.player2TextView);
        player2TextView.setText(game.getPlayer2FullName());

    }

    public void onIncreaseResult1Button1Click(View view){
        if(!game.getIsStarted()){
            initializeStart(1);
        }
        else {
            game.increaseResult(true, 1, true);
        }
        updateState();
    }
    public void onIncreaseResult1Button2Click(View view){
        if(!game.getIsStarted()){
            initializeStart(2);
        }
        else {
            game.increaseResult(false, 1, true);
        }
        updateState();
    }
    public void onIncreaseResult2Button1Click(View view){
        game.increaseResult(true, 2, true);
        updateState();
    }
    public void onIncreaseResult2Button2Click(View view){
        game.increaseResult(false, 2, true);
        updateState();
    }
    private void initializeStart(int player_id){
        Button increase1Btn1 = findViewById(R.id.increaseResult1Button1);
        Button increase1Btn2 = findViewById(R.id.increaseResult1Button2);
        Button increase2Btn1 = findViewById(R.id.increaseResult2Button1);
        Button increase2Btn2 = findViewById(R.id.increaseResult2Button2);
        increase1Btn1.setText("+1");
        increase1Btn2.setText("+1");
        increase2Btn1.setVisibility(View.VISIBLE);
        increase2Btn2.setVisibility(View.VISIBLE);
        game.startGame(player_id);
    }

    private void updateState(){
        TextView player1TextView = findViewById(R.id.player1TextView);
        TextView player2TextView = findViewById(R.id.player2TextView);
        if(game.getIsFinished()){
            player1TextView.setBackgroundColor(0xFF000000);
            player2TextView.setBackgroundColor(0xFF000000);
        }
        else{
            if(game.getPlayer1IsOnServe()){
                player1TextView.setBackgroundColor(0xFFFF0000);
                player2TextView.setBackgroundColor(0xFF000000);
            }
            else {
                player1TextView.setBackgroundColor(0xFF000000);
                player2TextView.setBackgroundColor(0xFFFF0000);
            }
        }

        TextView gameScoreTextView = findViewById(R.id.gameScoreTextView);
        TextView setScoreTextView = findViewById(R.id.setScoreTextView);

        String gameScoreString = game.getPlayer1WonSets() + " : " + game.getPlayer2WonSets();
        String setScoreString;
        if(game.getIsFinished()){
            setScoreString = "Game Over";
            Button increase1Button1 = findViewById(R.id.increaseResult1Button1);
            Button increase1Button2 = findViewById(R.id.increaseResult1Button2);
            Button increase2Button1 = findViewById(R.id.increaseResult2Button1);
            Button increase2Button2 = findViewById(R.id.increaseResult2Button2);
            increase1Button1.setVisibility(View.INVISIBLE);
            increase1Button2.setVisibility(View.INVISIBLE);
            increase2Button1.setVisibility(View.INVISIBLE);
            increase2Button2.setVisibility(View.INVISIBLE);
        }
        else{
            setScoreString = game.getPlayer1Points()[game.getSetNumber()] + " : " + game.getPlayer2Points()[game.getSetNumber()];
        }


        gameScoreTextView.setText(gameScoreString);
        setScoreTextView.setText(setScoreString);
    }

}
