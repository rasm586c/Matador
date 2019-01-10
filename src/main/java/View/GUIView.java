package View;

import Model.*;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIView implements View {
    private GUI gui;

    public GUIView()
    {
        gui = new GUI(createDefaultFields(40), Color.decode("#801515"));
    }

    public String getUserInput(String message) {
        return gui.getUserString(message);
    }

    public void updatePlayers(Player[] players) {}
    public void updateBoard(GameBoard board) {}

    private GUI_Field[] createDefaultFields(int fields) {
        Model.Field defaultField = new Model.Field("Start", 1, "Start", Model.Field.GUI_Type.Start);

        GUI_Field[] gui_field = new GUI_Field[fields];
        for (int i = 0; i < fields; i++) {
            gui_field[i] = fieldToGUI(defaultField);
        }

        return gui_field;
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
