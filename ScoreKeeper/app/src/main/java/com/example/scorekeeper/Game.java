package com.example.scorekeeper;

import java.io.Serializable;
import java.math.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Timer;

@SuppressWarnings("serial")
public class Game implements Serializable{
    private int gameID;
    private int refereeID;
    private int player1ID;
    private int player2ID;
    private int bestOf;
    private int player1WonSets;
    private int player2WonSets;
    private int[] player1Points;
    private int[] player2Points;
    private boolean isStarted;
    private boolean isFinished;
    private boolean player1IsOnServe;
    private boolean player1StartedGame;
    private int serveNumber;
    private int setNumber;
    private int countOfServes;
    private String date;
    private String time;
    private int tableNumber;

    public Game(){
        gameID = 1;
        refereeID = 2;
        player1ID = 3;
        player2ID = 4;
        bestOf = 3;
        player1WonSets = 0;
        player2WonSets = 0;
        player1Points = new int[bestOf];
        player2Points = new int[bestOf];
        for(int i = 0; i < bestOf; i++){
            player1Points[i] = 0;
            player2Points[i] = 0;
        }
    }

    public Game(int game_id, int referee_id, int player1_id, int player2_id, int best_of, int count_of_serves, String game_date, String game_time, int table_number){

        gameID = game_id;
        refereeID = referee_id;
        player1ID = player1_id;
        player2ID = player2_id;
        bestOf = best_of;
        player1WonSets = 0;
        player2WonSets = 0;
        player1Points = new int[bestOf];
        player2Points = new int[bestOf];
        for(int i = 0; i < bestOf; i++){
            player1Points[i] = 0;
            player2Points[i] = 0;
        }
        isFinished = false;
        isStarted = false;
        player1IsOnServe = true;
        player1StartedGame = true;
        serveNumber = 0;
        setNumber = 0;
        countOfServes = count_of_serves;
        date = game_date;
        time = game_time;
        tableNumber = table_number;


    }

    public Game(int id){
        gameID = 1;
        refereeID = 1;
        player1ID = id;
        player2ID = id;
        bestOf = 3;
        player1WonSets = 0;
        player2WonSets = 0;
        player1Points = new int[bestOf];
        player2Points = new int[bestOf];
        for(int i = 0; i < bestOf; i++){
            player1Points[i] = 0;
            player2Points[i] = 0;
        }
    }
    public int getGameID(){
        return gameID;
    }
    public int getRefereeID(){
        return refereeID;
    }
    public int getPlayer1ID(){
        return player1ID;
    }
    public int getPlayer2ID(){
        return player2ID;
    }
    public int getBestOf(){
        return bestOf;
    }

    public int getPlayer1WonSets(){
        return player1WonSets;
    }
    public int getPlayer2WonSets(){
        return player2WonSets;
    }
    public int[] getPlayer1Points(){
        return player1Points;
    }
    public int[] getPlayer2Points(){
        return player2Points;
    }

    public boolean getIsStarted() {return isStarted;}
    public boolean getIsFinished() {return isFinished;}
    public boolean getPlayer1IsOnServe(){return player1IsOnServe;}
    public int getServeNumber() {return serveNumber;}
    public String getDate() {return date;}
    public String getTime() {return time;}
    public int getTableNumber(){return tableNumber;}
    public int getSetNumber() {return setNumber;}


    // funkcija neimplementuota
    public void increaseResult(boolean player1, int pts, boolean switch_serve){
        if(player1){
            player1Points[setNumber] += pts;
        }
        else {
            player2Points[setNumber] += pts;
        }
        if (isItEndOfSet()){
            if(player1Points[setNumber] > player2Points[setNumber]){
                switchSet(1);
            }
            else{
                switchSet(2);
            }
            if(isITEndOfGame()){
                isFinished = true;
            }
        }
        else{
            switchServe();
        }

    }


    //------------------------------------------
    // Funkcijos implementuotos, bet netestuotos
    //------------------------------------------

    //-----Metodai zaidimo eigai valdyti-----

    // Metodas perjungia serva
    private void switchServe(){
        serveNumber++;
        if(serveNumber > countOfServes){
            serveNumber = 1;
            switchPlayer();
        }
    }

    // Metodas tikrina ar dar ne seto pabaiga
    private boolean isItEndOfSet(){
        if(Math.max(player1Points[setNumber], player2Points[setNumber]) > 16)
            return true;
        if(Math.abs(player1Points[setNumber] - player2Points[setNumber]) >= 2 && Math.max(player1Points[setNumber], player2Points[setNumber]) > 10)
            return true;
        return false;
    }

    // Metodas tikrina ar dar ne zaidimo pabaiga
    private boolean isITEndOfGame(){
        if(Math.max(player1WonSets, player2WonSets) >= (bestOf / 2) + 1)
            return true;
        return false;
    }

    // Metodas pakeicia paduodanti zaideja
    private void switchPlayer(){
        player1IsOnServe = !player1IsOnServe;
    }

    // Metodas perjungia seta
    private void switchSet(int winner){
        if(winner == 1){
            player1WonSets++;
        }
        else {
            player2WonSets++;
        }
        setNumber++;
        if((setNumber % 2) == 1){
            player1IsOnServe = !player1StartedGame;
        }
        else {
            player1IsOnServe = player1StartedGame;
        }
        serveNumber = 1;

    }

    //-----Kiti metodai-----

    // Metodas inicializuoja darbo pradzia
    public void startGame(int starting_player){
        if(!isStarted){
            isStarted = true;
            if(starting_player == 1){
                player1IsOnServe = true;
                player1StartedGame = true;
            }
            else {
                player1IsOnServe = false;
                player1StartedGame = false;
            }
            serveNumber = 1;

        }
    }
}
