package View;

import Model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements View {
    LanguagePack languagePack;

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
        System.out.println(languagePack.getString("choose_menu"));
        int number = 0;

        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                number = s.nextInt();

                if (number < args.length)
                    break;

                System.out.println(languagePack.getString("invalid_menu"));
            } catch (InputMismatchException ime) {
                System.out.println(languagePack.getString("invalid_number"));
            }
        }

        return args[number];
    }

    public void printDiceRoll(int value1, int value2) {
        System.out.println(languagePack.getString("your_roll") + value1 + languagePack.getString("and") + value2);
    }

    public void updateHouse(int fieldPosition, int houseCounter) {
        System.out.println(languagePack.getString("house_placed_on_field") + fieldPosition + languagePack.getString("there_are_now") + houseCounter + languagePack.getString("house_on_field"));
    }

    public void movePlayer(int oldPosition, int newPosition, Player player) { }

    public void setPlayerBalance(Player player, int balance) {
        System.out.println(languagePack.getString("the_player") + player.getName() + languagePack.getString("has_now") + balance + languagePack.getString("currency"));
    }

    public void setGameWon(String winner) {
        System.out.println(winner + languagePack.getString("player_win"));
    }

    public void removePlayer(Player player) {
        System.out.println(player.getName() + languagePack.getString("player_loss"));
    }

    public void updateOwner(Player player, int position, boolean active) {
        if (player != null) {
            if (active)
                System.out.println(languagePack.getString("the_player") + player.getName() + languagePack.getString("own_field_position") + position);
            else
                System.out.println(languagePack.getString("the_player") + player.getName() + languagePack.getString("mortgage_position") + position);
        }
    }

    public void print(String print) {
        System.out.println(print);
    }

    public void updatePlayers(Player[] players) { }
    public void updateBoard(GameBoard board) { }
}
