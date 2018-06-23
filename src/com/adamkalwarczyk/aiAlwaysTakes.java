package com.adamkalwarczyk;

public class aiAlwaysTakes implements Strategy {
    @Override
    public String aiGameplay(String[][] boardArray){
        return "Chosen gameplay type where ai always takes pawns";
    }
}
