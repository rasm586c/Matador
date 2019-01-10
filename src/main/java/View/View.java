package View;

import Model.*;

public interface View {
    String getUserInput(String message);
    String getUserSelect(String message, String... args);

    void print(String message);

    void updatePlayers(Player[] players);
    void movePlayer(int oldPosition, int newPosition, Player player);

    void printDiceRoll(int value1, int value2);

    void updateBoard(GameBoard board);
}
