package View;

import Model.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIView implements View {
    private GUI gui;
    private LanguagePack languagePack;

    private Player[] players;
    private GUI_Player[] guiPlayers;

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
        int playerIndex = findPlayerIndex(player);

        gui.getFields()[oldPosition].setCar(guiPlayers[playerIndex], false);
        gui.getFields()[newPosition].setCar(guiPlayers[playerIndex], true);
    }

    public void updateBoard(GameBoard board) {
        if (gui != null) gui.close();
        gui = new GUI(fieldsToGUI(board.getFields()), Color.decode("#801515"));
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
            case Street_Brown: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 1", new Color(134, 69, 18), Color.BLACK);
            case Street_Cyan: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 1", Color.CYAN, Color.BLACK);
            case Street_Purple: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 2", Color.MAGENTA, Color.BLACK);
            case Street_Orange: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 2", Color.ORANGE, Color.BLACK);
            case Street_Red: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 3", Color.RED, Color.BLACK);
            case Street_Yellow: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 3", Color.YELLOW, Color.BLACK);
            case Street_Green: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 4", Color.GREEN, Color.BLACK);
            case Street_Blue: return new GUI_Street(field.name, priceTxt, field.fieldText, "Leje: 5", Color.BLUE, Color.BLACK);
            case Brewery: return new GUI_Brewery("default", field.name, "", field.fieldText, "", Color.BLACK, Color.WHITE);
            case Jail: return new GUI_Jail("default", field.name, "", field.fieldText, new Color(68, 68, 68), Color.BLACK);
            case Chance: return new GUI_Chance(field.name, "", field.fieldText, new Color(204, 182, 0), Color.BLACK);
            case Refuge: return new GUI_Refuge("default", field.name, "", field.fieldText, Color.WHITE, Color.BLACK);
            case Start: return new GUI_Tax(field.name, "+2 til dig", field.fieldText, Color.GRAY, Color.BLACK);
            case Empty: return new GUI_Empty();
        }

        throw new IllegalArgumentException();
    }
}
