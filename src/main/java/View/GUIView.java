package View;

import Model.*;
import Model.Fields.Field;
import Model.Fields.PropertyField;
import Model.Fields.ShippingField;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.Random;

public class GUIView implements View {
    private GUI gui;
    private LanguagePack languagePack;

    private GameBoard board;

    private Player[] players;
    private GUI_Player[] guiPlayers;

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

    public void updateOwner(Player player, int position, boolean active) {
        //gui.getFields()[position].setTitle(fieldToGUI(board.getFields()[position]).getTitle() + "(" + player.getName() + ")");
        if (gui.getFields()[position] instanceof GUI_Ownable) {
            GUI_Ownable ownable = (GUI_Ownable) gui.getFields()[position];

            if (player == null) {
                ownable.setOwnerName(null);
                ownable.setBorder(null);
            } else {
                ownable.setOwnerName(player.getName());
                if (active)
                    ownable.setBorder(player.color);
                else
                    ownable.setBorder(player.color, Color.BLACK);
            }
        }
    }
    public void updatePlayers(Player[] players) {
        this.players = players;
        guiPlayers = createGuiPlayer(players);

        for (int i = 0; i < players.length; i++) {
            gui.addPlayer(guiPlayers[i]);
            gui.getFields()[0].setCar(guiPlayers[i], true);
        }
    }

    public void removePlayer(Player player) {
        gui.getFields()[player.getPositionClamped()].setCar(guiPlayers[findPlayerIndex(player)], false);
    }

    public void movePlayer(int oldPosition, int newPosition, Player player) {
        oldPosition = Player.clampPosition(oldPosition);
        newPosition = Player.clampPosition(newPosition);

        // CalculateDelta
        int delta = 0;
        int currentPosition = oldPosition;
        while (true) {
            currentPosition++;
            delta++;
            if (Player.clampPosition(currentPosition) == newPosition) break;
        }

        int movedDelta = 0;

        if (animateCar && delta <= 13)
        {
            while (movedDelta < delta) {
                moveCar(oldPosition + movedDelta, oldPosition + movedDelta + 1, player);
                sleep(100);

                movedDelta++;
            }


        } else {
            moveCar(oldPosition, newPosition, player);
        }
    }
    private void moveCar(int oldPos, int newPos, Player player) {
        int playerIndex = findPlayerIndex(player);

        gui.getFields()[Player.clampPosition(oldPos)].setCar(guiPlayers[playerIndex], false);
        gui.getFields()[Player.clampPosition(newPos)].setCar(guiPlayers[playerIndex], true);
    }
    public void updateBoard(GameBoard board) {
        if (gui != null) gui.close();
        gui = new GUI(fieldsToGUI(board.getFields()), Color.decode("#C0C0C0"));
        this.board = board;
    }

    public void setPlayerBalance(Player player, int balance) {
        int playerIndex = findPlayerIndex(player);
        guiPlayers[playerIndex].setBalance(balance);
    }
    public void updateHouse(int fieldPosition, int houseCounter) {
        GUI_Street street = (GUI_Street)gui.getFields()[fieldPosition];
        if (houseCounter >= 5) {
            street.setHouses(0);
            street.setHotel(true);
        } else {
            street.setHotel(false);
            street.setHouses(houseCounter);
        }
    }

    public void setGameWon(String winnerName) {
        StringBuilder wonText = new StringBuilder(winnerName + languagePack.getString("player_win"));
        int wonTextPosition = 0;
        int textPosition = 0;

        // Epilepsy Simulator!!!!!!
        Random rnd = new Random();
        while (true) {
            wonTextPosition++;
            textPosition = 0;

            for (int i = 0; i < gui.getFields().length; i++) {
                sleep(5);

                if (textPosition < wonText.length()) {
                    gui.getFields()[Player.clampPosition(i + wonTextPosition)].setTitle(Character.toString(wonText.charAt(textPosition)));

                } else {
                    gui.getFields()[Player.clampPosition(i + wonTextPosition)].setTitle(" ");
                }
                textPosition++;

                rnd = new Random(rnd.nextInt(Integer.MAX_VALUE));
                gui.getFields()[i].setBackGroundColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));

                gui.setDice(rnd.nextInt(6), rnd.nextInt(6));

                if (gui.getFields()[i] instanceof GUI_Street) {
                    updateHouse(i, rnd.nextInt(5));
                }

                if (gui.getFields()[i] instanceof GUI_Ownable) {
                    GUI_Ownable ownable = (GUI_Ownable) gui.getFields()[i];
                    ownable.setBorder(
                            new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)),
                            new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255))
                    );
                }
            }
        }
    }

    private void sleep(int n) {
        long t0 = System.currentTimeMillis();

        long t1;
        do {
            t1 = System.currentTimeMillis();
        } while(t1 - t0 < (long)n);

    }

    private GUI_Player toGuiPlayer(Player player) {
        PlayerType playerType =  player.getPlayerType();
        GUI_Car.Type guiPlayerType = Enum.valueOf(GUI_Car.Type.class, playerType.toString().toUpperCase());

        GUI_Player guiPlayer = new GUI_Player(player.getName(), 0, new GUI_Car(player.color, Color.WHITE, guiPlayerType, GUI_Car.Pattern.FILL));
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

    private GUI_Field[] fieldsToGUI(Field[] fields) {
        GUI_Field[] guiFields = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++){
            guiFields[i] = fieldToGUI(fields[i]);
        }
        return guiFields;
    }
    private GUI_Field fieldToGUI(Field field) {
        String priceTxt = String.format(" %s ", field.value);

        String fieldTxt = field.fieldText;

        if (field instanceof PropertyField)
            fieldTxt = String.format(fieldTxt,
                    String.format("%d\n", ((PropertyField) field).getRentPrices()[0]),
                    String.format("%d\n", ((PropertyField) field).getRentPrices()[1]),
                    String.format("%d\n", ((PropertyField) field).getRentPrices()[2]),
                    String.format("%d\n", ((PropertyField) field).getRentPrices()[3]),
                    String.format("%d\n", ((PropertyField) field).getRentPrices()[4]),
                    String.format("%d\n", ((PropertyField) field).getRentPrices()[5]),
                    String.format("%d\n", 300),
                    String.format("%d\n", 400)
            );



        if (field instanceof ShippingField)
            fieldTxt = String.format(fieldTxt,
                    String.format("%d\n", 100),
                    String.format("%d\n", 200),
                    String.format("%d\n", 300),
                    String.format("%d\n", 400)
            );

        switch (field.fieldType) {
            case Street_Cyan: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 1", new Color(30, 151, 156), Color.BLACK);
            case Street_Pink: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 1", new Color(159, 105, 154), Color.BLACK);
            case Street_Green: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 2", new Color(76, 146, 77), Color.BLACK);
            case Street_Blue: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 2", new Color(77, 102, 162), Color.BLACK);
            case Street_Red: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 3", new Color(147, 63, 25), Color.BLACK);
            case Street_White: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 3", new Color(166, 171, 131), Color.BLACK);
            case Street_Yellow: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 4", new Color(187, 181, 13), Color.BLACK);
            case Street_Brown: return new GUI_Street(field.name, priceTxt, fieldTxt, "Leje: 5", new Color(122, 53, 153), Color.BLACK);
            case Brewery: return new GUI_Brewery("default", field.name, "", fieldTxt, "", Color.BLACK, Color.WHITE);
            case Jail: return new GUI_Jail("default", field.name, "", fieldTxt, new Color(68, 68, 68), Color.BLACK);
            case Chance: return new GUI_Chance(field.name, "", fieldTxt, new Color(51, 204, 0), Color.BLACK);
            case Refuge: return new GUI_Refuge("default", field.name, "", fieldTxt, Color.WHITE, Color.BLACK);
            case Start: return new GUI_Tax(field.name, "4000 kr.", fieldTxt, new Color(204, 105, 19), Color.BLACK);
            case Empty: return new GUI_Empty();
            case Tax: return new GUI_Tax(field.name,priceTxt,fieldTxt,new Color(75, 139, 118),Color.BLACK);
            case Shipping: return new GUI_Shipping("default",field.name,priceTxt,fieldTxt,"Leje: 2", Color.WHITE,Color.BLACK);
            case Loan: return new GUI_Tax(field.name,priceTxt,fieldTxt,new Color(99, 124, 168),Color.BLACK);
        }
        throw new IllegalArgumentException();
    }
}
