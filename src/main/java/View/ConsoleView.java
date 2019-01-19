package View;

import Model.*;

public class ConsoleView implements View {
    public String getUserInput(String message) {
        System.out.println(message);
        return "";
    }

    public String getUserSelect(String message, String... args) {
        return "";
    }

    public void printDiceRoll(int value1, int value2) {
        System.out.println("You rolled " + value1 + " and " + value2);
    }

    public void updateHouse(int fieldPosition, int houseCounter) {

    }

    public void movePlayer(int oldPosition, int newPosition, Player player) { }

    public void setPlayerBalance(Player player, int balance) {}

    public void setGameWon(String winner) {

    }

    public void updateOwner(Player player, int position, boolean active) {


    }

    public void print(String print) {
        System.out.println(print);
    }

    public void updatePlayers(Player[] players) {}
    public void updateBoard(GameBoard board) {}
}
