/*
TIC TAC TOE Game
* June 18, 2024
  June 28, 2024
  July 01, 2024
* author: Eniel Leba
* tableau[row][col]
* */

import java.util.Scanner;
import java.util.Random;

public class Main{
    static Player player1;
    static Player player2;
    static Table tableau = new Table();
    static boolean bot = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String newGame, name;
        int[][] tableau = new int[3][3];

        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("New Game");
        System.out.println("Do you want to play with a bot?");
        System.out.print("Enter 'y' for yes and 'n' for no: ");
        String wBot = scanner.nextLine();
        while (wBot.toLowerCase().compareTo("y") != 0 && wBot.toLowerCase().compareTo("n") != 0){
            System.out.println("Sorry only 'y' and 'n' are allowed");
            System.out.println("Do you want to play with a Bot?");
            System.out.print("Enter 'y' for yes and 'n' for no: ");
            wBot = scanner.nextLine();
        }
        bot = wBot.toLowerCase().compareTo("y") == 0;
        System.out.print("enter first player name: ");
        name = scanner.nextLine();
        player1 = new Player(name);
        if(bot){
            player2 = new Player(true);
        } else {
            System.out.print("enter second player name: ");
            name = scanner.nextLine();
            player2 = new Player(name, true);
        }
        newGame();
        do {
            System.out.println("Do you want to play again?");
            System.out.print("Enter 'y' for yes and 'n' for no: ");
            newGame = scanner.nextLine();
            while (newGame.toLowerCase().compareTo("y") != 0 && newGame.toLowerCase().compareTo("n") != 0){
                System.out.println("Sorry only 'y' and 'n' are allowed");
                System.out.println("Do you want to play again?");
                System.out.print("Enter 'y' for yes and 'n' for no: ");
                newGame = scanner.nextLine();
            }
            if(newGame.toLowerCase().compareTo("n")==0) continue;
            newGame();
        }while (newGame.toLowerCase().compareTo("y")==0);
        printScore(true);
    }

    public static void newGame(){
        tableau.newTable();
        tableau.printTable();

        player1.setFirst(!player1.isFirst());
        player2.setFirst(!player2.isFirst());

        String endGame = playGame();
        System.out.println("Game Over!!!");
        System.out.println(endGame);
        printScore(false);
    }

    public static String playGame(){
        Scanner scanner = new Scanner(System.in);

        boolean isWon = false, isDraw = false;
        int row, col;
        Player player = player1.isTurnToPlay() ? player1 : player2;

        while(!isWon && !isDraw){
            player = player1.isTurnToPlay() ? player1 : player2;
            player2.setTurnToPlay(player.equals(player1));
            player1.setTurnToPlay(!player.equals(player1));

            System.out.println("It's " +(player.getName()) +"'s turn to play");
            if(bot && player.getName().toLowerCase().compareTo("computer") == 0){
                do {
                    Random random = new Random();
                    row = random.nextInt(3);
                    col = random.nextInt(3);
                } while(!tableau.isValid(row, col));
                System.out.println("Computer plays ("+(row+1)+" , "+(col+1)+")");
            }
            else {
                do {
                    System.out.print("enter valid row number(1, 2, 3): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("1, 2 or 3 only please!");
                        scanner.nextLine();
                    }
                    row = scanner.nextInt() - 1;
                    System.out.print("enter valid column number (1, 2, 3): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("1, 2 or 3 only please!");
                        scanner.nextLine();
                    }
                    col = scanner.nextInt() - 1;
                    System.out.println();
                } while (!tableau.isValid(row, col));
            }

            tableau.insertToken(row, col, player.getToken());
            isWon = tableau.isWon();
            isDraw = !isWon && tableau.isFull();

            tableau.printTable();
        }

        if(isWon) {
            if(player.equals(player1)) {
                player1.setScore();
                return (player1.getName() + " Won");
            }
            else {
                player2.setScore();
                return (player2.getName()+" Won");
            }
        }
        return "It Is A Draw";
    }

    public static void printScore(boolean isFinal){
        if(isFinal) System.out.println("Final Score:");
        else System.out.println("Score:");
        System.out.println(player1.getName() +" " +player1.getScore() +" - " +player2.getScore() +" " +player2.getName());
    }
}