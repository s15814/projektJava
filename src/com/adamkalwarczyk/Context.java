package com.adamkalwarczyk;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public String executeStrategy(String[][] boardArray) {
        return strategy.aiGameplay(boardArray);
    }


}
