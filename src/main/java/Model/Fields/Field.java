package Model.Fields;

import Model.GameState;
import Model.Player;

/**
 * This class is controlling each field.
 *
 * <p>
 * Each field has a name, a value and a description. While one field can give the player another turn.
 * </p>
 */
public abstract class Field {
    /**
     * Defines the name of the field.
     */
    public final String name;
    /**
     * Defines the value of the field.
     *
     * <p> The Value of the field, is how much money the player is getting or how much money the player is losing.</p>
     */
    public final int value;


    public final int rent;
    /**
     * Defines the fields description.
     */
    public final String fieldText;


    /**
     *
     * */
    private Player owner = null;

    /**
     *
     * */
    private int houseCounter = 0;

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() { return owner; }

    /*
    *
    * */
    public final GUI_Type fieldType;

    /**
     * This method is for creating fields, where the player can't get another turn.
     *
     * @param name      The name of the field.
     * @param value     The value of the field.
     * @param fieldText The description of the field.
     */
    public Field(String name, int value, int rent, String fieldText) {
        this(name, value, rent, fieldText, GUI_Type.Street);
    }

    public Field(String name, int value, int rent, String fieldText, GUI_Type fieldType) {
        this.name = name;
        this.value = value;
        this.fieldType = fieldType;
        this.rent = rent;

        if (fieldText == null) this.fieldText = "";
        else this.fieldText = fieldText;
    }

    public void onFieldLand(GameState state) {

    }

    public int getHouseCounter() { return houseCounter; }
    public void setHouseCounter(int houseCounter) { this.houseCounter = houseCounter; }

    public enum GUI_Type {
        Chance,
        Brewery,
        Jail,
        Shipping,
        Refuge,
        Street,
        Street_Cyan,
        Street_Pink,
        Street_Green,
        Street_Blue,
        Street_Red,
        Street_White,
        Street_Yellow,
        Street_Brown,
        Start,
        Empty,
        Tax,
        Loan

    }

}

