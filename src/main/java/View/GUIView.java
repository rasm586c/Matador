package View;

import Controller.TurnController;
import Model.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.io.ObjectInputStream;

import static Controller.TurnController.sleep;


public class GUIView implements View {
    private GUI gui;
    private LanguagePack languagePack;

    private Player[] players;
    private GUI_Player[] guiPlayers;

    static final int BOARD_SIZE = 40;
    private boolean animateCar = true;

    public GUIView(LanguagePack languagePack)
    {
        this.languagePack = languagePack;
        updateBoard(new GameBoard(this.languagePack));
    }

    public String getUserInput(String message) {
        return gui.getUserString(message);
    }

    public String getUserSelect(String message, String... args) {
        return gui.getUserSelection(message, args);
    }

    public void printDiceRoll(int value1, int value2) {
        gui.setDice(value1, value2);
    }

    public void print(String message) {
        gui.showMessage(message);
    }

    public void updatePlayers(Player[] players) {
        this.players = players;
        guiPlayers = createGuiPlayer(players);

        for (int i = 0; i < players.length; i++) {
            gui.getFields()[0].setCar(guiPlayers[i], true);
        }
    }



    public void movePlayer(int oldPosition, int newPosition, Player player) {
        if (animateCar)
        {
            if (oldPosition < newPosition) {
                for (int i = oldPosition; i < newPosition; i++) {
                    moveCar(i, i + 1, player);
                    sleep(100);
                }
            } else {
                for (int i = newPosition; i > oldPosition; i--) {
                    moveCar(i, i - 1, player);
                    sleep(100);
                }
            }
        } else {
            moveCar(oldPosition, newPosition, player);
        }
    }

    private void moveCar(int oldPos, int newPos, Player player) {
        int playerIndex = findPlayerIndex(player);

        gui.getFields()[clampPosition(oldPos)].setCar(guiPlayers[playerIndex], false);
        gui.getFields()[clampPosition(newPos)].setCar(guiPlayers[playerIndex], true);
    }

    public void updateBoard(GameBoard board) {
        if (gui != null) gui.close();
        gui = new GUI(fieldsToGUI(board.getFields()), Color.decode("#801515"));
    }

    /**
     * Creates a timeout of n miliseconds. Calling this will make the program stop and wait for n miliseconds and then allow further execution.
     * This method has no lock so prevent calling it from multiple threads.
     * */
    private void sleep(int n) {
        long t0 = System.currentTimeMillis();

        long t1;
        do {
            t1 = System.currentTimeMillis();
        } while(t1 - t0 < (long)n);

    }


    /**
     * This function recursively calls itself until it ensures the input value is between 0 and BOARD_SIZE (I.e. clamps a position to the board)
     * @param position
     * @return
     */
    private int clampPosition(int position) {
        if (position < 0) { return clampPosition(position + BOARD_SIZE); }
        if (position < BOARD_SIZE) return position;
        return clampPosition(position - BOARD_SIZE);
    }


    private GUI_Player toGuiPlayer(Player player) {
        PlayerType playerType =  player.getPlayerType();
        GUI_Car.Type guiPlayerType = Enum.valueOf(GUI_Car.Type.class, playerType.toString().toUpperCase());

        GUI_Player guiPlayer = new GUI_Player(player.getName(), 0, new GUI_Car(Color.BLACK, Color.WHITE, guiPlayerType, GUI_Car.Pattern.FILL));
        return guiPlayer;
    }
    private GUI_Player[] createGuiPlayer(Player[] players) {
        GUI_Player[] guiPlayers = new GUI_Player[players.length];
        for (int i = 0; i < players.length; i++) {
            guiPlayers[i] = toGuiPlayer(players[i]);
        }
        return guiPlayers;
    }
    private int findPlayerIndex(Player player) {
        for (int i = 0; i < players.length; i++) { if (player.equals(players[i])) return i; }
        return -1;
    }

    private GUI_Field[] fieldsToGUI(Model.Field[] fields) {
        GUI_Field[] guiFields = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++){
            guiFields[i] = fieldToGUI(fields[i]);
        }
        return guiFields;
    }
    private GUI_Field fieldToGUI(Model.Field field) {
        String priceTxt = String.format(" %s ", field.value);

        switch (field.fieldType) {
            case Street_Cyan: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 1", Color.CYAN, Color.BLACK);
            case Street_Pink: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 1", Color.PINK, Color.BLACK);
            case Street_Green: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 2", Color.GREEN, Color.BLACK);
            case Street_Blue: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 2", Color.BLUE, Color.BLACK);
            case Street_Red: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 3", Color.RED, Color.BLACK);
            case Street_White: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 3", Color.WHITE, Color.BLACK);
            case Street_Yellow: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 4", Color.YELLOW, Color.BLACK);
            case Street_Brown: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 5", new Color(144, 48, 15), Color.BLACK);
            case Brewery: return new GUI_Brewery("default", field.name, "", field.fieldText, "", Color.BLACK, Color.WHITE);
            case Jail: return new GUI_Jail("default", field.name, "", field.fieldText, new Color(68, 68, 68), Color.BLACK);
            case Chance: return new GUI_Chance(field.name, "", field.fieldText, new Color(204, 182, 0), Color.BLACK);
            case Refuge: return new GUI_Refuge("default", field.name, "", field.fieldText, Color.WHITE, Color.BLACK);
            case Start: return new GUI_Tax(field.name, "+2 til dig", field.fieldText, Color.GRAY, Color.BLACK);
            case Empty: return new GUI_Empty();
            case Tax: return new GUI_Tax(field.name,priceTxt,field.fieldText,Color.lightGray,Color.BLACK);
        }

        throw new IllegalArgumentException();
    }
}
