package com.adamkalwarczyk;

public class aiAlwaysTakes implements Strategy {
    @Override
    public String aiGameplay(){
        return "Chosen gameplay type where ai always takes pawns";
    }
}
