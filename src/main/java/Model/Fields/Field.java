package Model.Fields;

import Model.GameState;

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

    /**
     * Defines the fields description.
     */
    public final String fieldText;

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
    public Field(String name, int value, String fieldText) {
        this(name, value, fieldText, GUI_Type.Street);
    }

    public Field(String name, int value, String fieldText, GUI_Type fieldType) {
        this.name = name;
        this.value = value;
        this.fieldType = fieldType;

        if (fieldText == null) this.fieldText = "";
        else this.fieldText = fieldText;
    }

    public void onFieldLand(GameState state) {

    }

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
        Tax

    }

}

