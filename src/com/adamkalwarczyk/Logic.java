package com.adamkalwarczyk;

import java.util.List;
import java.util.Scanner;


public class Logic {

    private static String side;

    public static void chooseSide(Scanner scanner) {
        System.out.println("Type 'black' or 'white' to choose a side: ");

        side = scanner.nextLine().toLowerCase();

        switch(side) {
            case "black": {
                System.out.println("You chose black pieces");
                break;
            }
            case "white": {
                System.out.println("You chose white pieces");
                break;
            }
            default: {
                System.out.println("You must choose black or white");
                chooseSide(scanner);
            }
        }
    }
    public static String[][] doMove(String[][] boardArray, String moveFrom, String moveTo) {
        int columnFrom = convertColumn(moveFrom);
        int rowFrom = convertRow(moveFrom);
        int columnTo = convertColumn(moveTo);
        int rowTo = convertRow(moveTo);

        switch (side) {
            case "white": {
                if((rowFrom < rowTo) || (rowFrom - rowTo != 1) || (Math.abs(columnFrom-columnTo)) < 0 || (Math.abs(columnFrom-columnTo)) > 1) {
                    System.out.println("You can only move one field forward, or one field diagonally forward, try another move.");
                } else {
                    if (boardArray[rowTo][columnTo].equals("W")) {
                        System.out.println("Field occupied by one of your pieces, try another one.");
                    } else {
                        if (columnFrom == columnTo && boardArray[rowTo][columnTo].equals("B")) {
                            System.out.println("You can only take opponent's pieces diagonally, try another move.");
                        } else {
                            boardArray[rowFrom][columnFrom] = " ";
                            boardArray[rowTo][columnTo] = "W";
                            System.out.println("You moved a pawn from field " + moveFrom.toUpperCase() + " to field " + moveTo.toUpperCase() + ".");
                            side = "black";
                        }
                    }
                }
                break;
            }
            case "black": {
                if((rowFrom > rowTo) || (rowTo - rowFrom != 1) || (Math.abs(columnFrom-columnTo)) < 0 || (Math.abs(columnFrom-columnTo)) > 1) {
                    System.out.println("You can only move one field forward, or one field diagonally forward, try another move.");
                } else {
                    if (boardArray[rowTo][columnTo].equals("B")) {
                        System.out.println("Field occupied by one of your pieces, try another one.");
                    } else {
                        if (columnFrom == columnTo && boardArray[rowTo][columnTo].equals("W")) {
                            System.out.println("You can only take opponent's pieces diagonally, try another move.");
                        } else {
                            boardArray[rowFrom][columnFrom] = " ";
                            boardArray[rowTo][columnTo] = "B";
                            System.out.println("You moved a pawn from field " + moveFrom.toUpperCase() + " to field " + moveTo.toUpperCase() + ".");
                            side = "white";
                        }
                    }
                }
                break;
            }
        }

        return boardArray;

    }

    public static String movePickPawn(Scanner scanner, String[][] boardArray) {

        boolean checker = true;

        switch (side) {
            case "white": {
                while (checker) {
                    System.out.println("Choose which pawn to move: ");
                    String field = scanner.nextLine();
                    if (field.toLowerCase().matches("[abcdefgh][12345678]")) {
                        if (boardArray[convertRow(field)][convertColumn(field)].equals("W")) {
                            checker = false;
                            return field;
                        } else {
                            System.out.println("You must move a white piece, enter a field containing one of your pieces");
                        }
                    }else {
                        System.out.println("Please enter a valid field name, containing letters between a and h, follower by a number from 1 to 8");
                    }
                }
            }
            case "black": {
                while (checker) {
                    System.out.println("Choose which pawn to move: ");
                    String field = scanner.nextLine();
                    if (field.toLowerCase().matches("[abcdefgh][12345678]")) {
                        if (boardArray[convertRow(field)][convertColumn(field)].equals("B")) {
                            checker = false;
                            return field;
                        } else {
                            System.out.println("You must move a white piece, enter a field containing one of your pieces");
                        }
                    }else {
                        System.out.println("Please enter a valid field name, containing letters between a and h, follower by a number from 1 to 8");
                    }
                }
            }
        }
        return "error";
    }

    public static String movePickEndPosition(Scanner scanner) {
        boolean checker = true;
        while (checker) {
            System.out.println("Choose where to move the selected pawn: ");
            String field = scanner.nextLine();
            if (field.toLowerCase().matches("[abcdefgh][12345678]")) {
                checker = false;
                return field;
            }else {
                System.out.println("Please enter a valid field name, containing letters between a and h, follower by a number from 1 to 8");
            }
        }
        return "error";
    }

    public static boolean checkWinCondition(String[][] boardArray) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardArray[i][j].equals("W")){
                    System.out.println("White won, congratulations!");
                    return false;
                }
            }
        }
        for (int i = 7; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardArray[i][j].equals("B")){
                    System.out.println("Black won, congratulations!");
                    return false;
                }
            }
        }
        return true;
    }

    public static List<String> findValidAiMove(String[][] boardArray, List<String> optionsArray) {
        optionsArray.clear();
        switch (side) {
            case "white": {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (boardArray[i][j].equals("W")) {
                            if (i > 0){
                                if (j == 0) {
                                    if (boardArray[i - 1][j].equals(" ")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j));
                                    }
                                    if (boardArray[i - 1][j + 1].equals(" ") || boardArray[i - 1][j + 1].equals("B")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j + 1));
                                    }
                                } else if (j == 7) {
                                    if (boardArray[i - 1][j].equals(" ")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j));
                                    }
                                    if (boardArray[i - 1][j - 1].equals(" ") || boardArray[i - 1][j - 1].equals("B")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j - 1));
                                    }
                                } else {
                                    if (boardArray[i - 1][j].equals(" ")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j));
                                    }
                                    if (boardArray[i - 1][j - 1].equals(" ") || boardArray[i - 1][j - 1].equals("B")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j - 1));
                                    }
                                    if (boardArray[i - 1][j + 1].equals(" ") || boardArray[i - 1][j + 1].equals("B")) {
                                        optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i - 1) + String.valueOf(j + 1));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return optionsArray;
        case "black": {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boardArray[i][j].equals("B")) {
                        if (i < 7){
                            if (j == 0) {
                                if (boardArray[i + 1][j].equals(" ")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j));
                                }
                                if (boardArray[i + 1][j + 1].equals(" ") || boardArray[i + 1][j + 1].equals("W")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j + 1));
                                }
                            } else if (j == 7) {
                                if (boardArray[i + 1][j].equals(" ")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j));
                                }
                                if (boardArray[i + 1][j - 1].equals(" ") || boardArray[i + 1][j - 1].equals("W")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j - 1));
                                }
                            } else {
                                if (boardArray[i + 1][j].equals(" ")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j));
                                }
                                if (boardArray[i + 1][j - 1].equals(" ") || boardArray[i + 1][j - 1].equals("W")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j - 1));
                                }
                                if (boardArray[i + 1][j + 1].equals(" ") || boardArray[i + 1][j + 1].equals("W")) {
                                    optionsArray.add(String.valueOf(i) + String.valueOf(j) + String.valueOf(i + 1) + String.valueOf(j + 1));
                                }
                            }
                        }
                    }
                }
            }


            break;
        }
    }

        return optionsArray;
    }

    public static void choseGameplayMode(Scanner scanner) {
        boolean checker = true;
        while (checker) {
            System.out.println("Choose the gameplay mode. Type 1 for player versus player, 2 for player versus random ai, 3 for player versus aggressive ai: ");
            String field = scanner.nextLine();
            if (field.toLowerCase().matches("[123]")) {
                checker = false;
                switch (field) {
                    case "1": {
                        System.out.println("You chose player versus player.");
                        break;
                    }
                    case "2": {
                        System.out.println("You chose the random ai as your opponent");
                        Context context = new Context(new aiRandom());
                        break;
                    }
                    case "3": {
                        System.out.println("You chose the aggressive ai as your opponent");
                        Context context = new Context(new aiAlwaysTakes());
                        break;
                    }
                }
            } else {
                System.out.println("Please enter a valid choice.");
            }
        }

    }

// zmiana litery ze wspolrzednych pola na cyfre
    public static int convertColumn(String field) {
        int column = 0;
        switch(field.substring(0,1).toLowerCase()){
            case "a":
                column = 0;
                break;
            case "b" :
                column = 1;
                break;
            case "c" :
                column = 2;
                break;
            case "d":
                column = 3;
                break;
            case "e" :
                column = 4;
                break;
            case "f" :
                column = 5;
                break;
            case "g":
                column = 6;
                break;
            case "h" :
                column = 7;
                break;
        }
        return column;
    }
// zmiana cyfry ze wspolrzednych pola na odpowiednia do przemieszczania sie w tablicy
    public static int convertRow(String field) {
        int row = 0;
        switch(field.substring(1)){
            case "1":
                row = 7;
                break;
            case "2" :
                row = 6;
                break;
            case "3" :
                row = 5;
                break;
            case "4":
                row = 4;
                break;
            case "5" :
                row = 3;
                break;
            case "6" :
                row = 2;
                break;
            case "7":
                row = 1;
                break;
            case "8" :
                row = 0;
                break;
        }
        return row;
    }
//rysowanie planszy
    public static void printBoard(String[][] boardArray) {
        System.out.println("Currently playing: " + side);
        System.out.println("    A   B   C   D   E   F   G   H   ");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i);
            for (int j = 0; j < 8; j++) {
                System.out.print(" | " + boardArray[i][j]);
            }
            System.out.println(" | ");
            System.out.println("  - - - - - - - - - - - - - - - - - ");
        }
    }


}
