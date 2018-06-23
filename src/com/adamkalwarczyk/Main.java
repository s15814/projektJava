package com.adamkalwarczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int boardHeight = 8;
        int boardWidth = 8;
//      create the board and put the pieces in place
        String[][] myBoard = new String[boardHeight][boardWidth];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < boardWidth; j++) {
                myBoard[i][j] = "B";
            }
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < boardWidth; j++) {
                myBoard[i][j] = " ";
            }
        }

        for (int i = 6; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                myBoard[i][j] = "W";
            }
        }

        List<String> aiOptions = new ArrayList<>();


//        Context context = new Context(new aiAlwaysTakes());
//        System.out.println(context.executeStrategy());


        Logic.chooseSide(scan);
        Logic.choseGameplayMode(scan);


        boolean winCondition = true;

        while(winCondition) {
            aiOptions = Logic.findValidAiMove(myBoard, aiOptions);
            for (String move: aiOptions) {
                System.out.println(move);
            }
            Logic.printBoard(myBoard);
            myBoard = Logic.doMove(myBoard,Logic.movePickPawn(scan, myBoard), Logic.movePickEndPosition(scan));
            Logic.printBoard(myBoard);
            winCondition = Logic.checkWinCondition(myBoard);
        }

    }

}

