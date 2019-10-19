package com.example.scorekeeper;

import java.io.Serializable;

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



}
