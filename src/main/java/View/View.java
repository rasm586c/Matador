package View;

import Model.*;

public interface View {
    String getUserInput(String message);
    String getUserSelect(String message, String... args);

    void print(String message);

    void updatePlayers(Player[] players);
    void movePlayer(int oldPosition, int newPosition, Player player);

    void setGameWon(String winner);

    void updateOwner(Player player, int position, boolean active);

    void removePlayer(Player player);

    void setPlayerBalance(Player player, int balance);

    void printDiceRoll(int value1, int value2);

    void updateHouse(int fieldPosition, int houseCounter);

    void updateBoard(GameBoard board);
}
