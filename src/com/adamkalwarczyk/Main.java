package com.adamkalwarczyk;

import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int boardHeight = 8;
        int boardWidth = 8;

        String[][] myArray = new String[boardHeight][boardWidth];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < boardWidth; j++) {
                myArray[i][j] = "B";
            }
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < boardWidth; j++) {
                myArray[i][j] = " ";
            }
        }

        for (int i = 6; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                myArray[i][j] = "W";
            }
        }

        Logic.chooseSide(scan);
        Logic.printBoard(myArray);

        boolean winCondition = true;

        while(winCondition) {
            myArray = Logic.doMove(myArray,Logic.movePickPawn(scan, myArray), Logic.movePickEndPosition(scan));
            Logic.printBoard(myArray);
            winCondition = Logic.checkWinCondition(myArray);
        }

    }

}

