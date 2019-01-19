package View;

import Model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements View {
    public String getUserInput(String message) {
        System.out.println(message);

        Scanner s = new Scanner(System.in);
        String userInput = s.nextLine();
        return userInput;
    }

    public String getUserSelect(String message, String... args) {
        System.out.println(message);
        System.out.println("-----");
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + ". " + args[i]);
        }
        System.out.println("Skriv et tal for at vælge menupunkt");
        int number = 0;

        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                number = s.nextInt();

                if (number < args.length)
                    break;

                System.out.println("Ugyldigt menupunkt! Prøv igen.");
            } catch (InputMismatchException ime) {
                System.out.println("Ugyldigt tal! Prøv igen.");
            }
        }

        return args[number];
    }

    public void printDiceRoll(int value1, int value2) {
        System.out.println("Du slog " + value1 + " og " + value2);
    }

    public void updateHouse(int fieldPosition, int houseCounter) {
        System.out.println("Et hus er blevet sat på feltet med position " + fieldPosition + ". Der er nu " + houseCounter + " huse på den grund.");
    }

    public void movePlayer(int oldPosition, int newPosition, Player player) { }

    public void setPlayerBalance(Player player, int balance) {
        System.out.println("Spilleren '"+player.getName()+"' har nu " + balance + " kr");
    }

    public void setGameWon(String winner) {
        System.out.println(winner + " vandt spillet!");
    }

    public void removePlayer(Player player) {
        System.out.println(player.getName() + " har tabt!");
    }

    public void updateOwner(Player player, int position, boolean active) {
        if (player != null) {
            if (active)
                System.out.println("Spilleren " + player.getName() + " ejer nu feltet på position " + position);
            else
                System.out.println("Spilleren " + player.getName() + " har nu pantsat sin grund på position " + position);
        }
    }

    public void print(String print) {
        System.out.println(print);
    }

    public void updatePlayers(Player[] players) { }
    public void updateBoard(GameBoard board) { }
}
