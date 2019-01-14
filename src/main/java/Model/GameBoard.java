package Model;

public class GameBoard {
    private LanguagePack stringContainer;

    public GameBoard(LanguagePack stringContainer) {
        this.stringContainer = stringContainer;
    }

    /**
     * An array of the Gameboard fields. Model.Field variables include name, value, field text and field type.
     * Name and field text is found through our string container.
     */
    public Field[] getFields() {
        return new Field[]{
                new StartField(stringContainer.getString("field_start_title")),
                new PropertyField(stringContainer.getString("field_cyan_1_title"), 1, stringContainer.getString("field_cyan_1"), Field.GUI_Type.Street_Cyan),
                new PropertyField("C", 0, stringContainer.getString("field_chance"), Field.GUI_Type.Chance),
                new PropertyField(stringContainer.getString("field_cyan_2_title"), 1, stringContainer.getString("field_cyan_2"), Field.GUI_Type.Street_Cyan),
                new PropertyField(stringContainer.getString("field_indkomstskat_title"), 0, stringContainer.getString("field_indkomstskat"), Field.GUI_Type.Tax),
                new PropertyField(stringContainer.getString("field_ship_1_title"), 0, stringContainer.getString("field_ship_1"), Field.GUI_Type.Shipping),
                new PropertyField(stringContainer.getString("field_pink_1_title"), 0, stringContainer.getString("field_pink_1"), Field.GUI_Type.Street_Pink),
                new Field("C", 0, stringContainer.getString("field_chance"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_pink_2_title"), 1, stringContainer.getString("field_pink_2"), Field.GUI_Type.Street_Pink),
                new Field(stringContainer.getString("field_pink_3_title"), 1, stringContainer.getString("field_pink_3"), Field.GUI_Type.Street_Pink),
                new Field(stringContainer.getString("field_jail_title"), 1, stringContainer.getString("field_jail"), Field.GUI_Type.Jail),
                new Field(stringContainer.getString("field_green_1_title"), 1, stringContainer.getString("field_green_1"), Field.GUI_Type.Street_Green),
                new Field(stringContainer.getString("field_brewery_1_title"), 1, stringContainer.getString("field_brewery_1"), Field.GUI_Type.Brewery),
                new Field(stringContainer.getString("field_green_2_title"), 1, stringContainer.getString("field_green_2"), Field.GUI_Type.Street_Green),
                new Field(stringContainer.getString("field_green_3_title"), 1, stringContainer.getString("field_green_2"), Field.GUI_Type.Street_Green),
                new Field(stringContainer.getString("field_ship_2_title"), 0, stringContainer.getString("field_ship_2"), Field.GUI_Type.Shipping),
                new Field(stringContainer.getString("field_blue_1_title"), 1, stringContainer.getString("field_blue_1"), Field.GUI_Type.Street_Blue),
                new Field("C", 0, stringContainer.getString("field_chance"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_blue_2_title"), 1, stringContainer.getString("field_blue_2"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_blue_3_title"), 1, stringContainer.getString("field_blue_3"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_refuge_title"), 0, stringContainer.getString("field_refuge"), Field.GUI_Type.Refuge),
                new Field(stringContainer.getString("field_red_1_title"), 1, stringContainer.getString("field_red_1"), Field.GUI_Type.Street_Red),
                new Field("C", 0, stringContainer.getString("field_chance"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_red_2_title"), 1, stringContainer.getString("field_red_2"), Field.GUI_Type.Street_Red),
                new Field(stringContainer.getString("field_red_3_title"), 1, stringContainer.getString("field_red_3"), Field.GUI_Type.Street_Red),
                new Field(stringContainer.getString("field_ship_3_title"), 0, stringContainer.getString("field_ship_3"), Field.GUI_Type.Shipping),
                new Field(stringContainer.getString("field_white_1_title"), 1, stringContainer.getString("field_white_1"), Field.GUI_Type.Street_White),
                new Field(stringContainer.getString("field_white_2_title"), 1, stringContainer.getString("field_white_2"), Field.GUI_Type.Street_White),
                new Field(stringContainer.getString("field_brewery_2_title"), 1, stringContainer.getString("field_brewery_2"), Field.GUI_Type.Brewery),
                new Field(stringContainer.getString("field_white_3_title"), 1, stringContainer.getString("field_white_3"), Field.GUI_Type.Street_White),
                new Field(stringContainer.getString("field_jail_title"), 1, stringContainer.getString("field_jail"), Field.GUI_Type.Jail),
                new Field(stringContainer.getString("field_yellow_1_title"), 1, stringContainer.getString("field_yellow_1"), Field.GUI_Type.Street_Yellow),
                new Field(stringContainer.getString("field_yellow_2_title"), 1, stringContainer.getString("field_yellow_2"), Field.GUI_Type.Street_Yellow),
                new Field("C", 0, stringContainer.getString("field_chance"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_yellow_3_title"), 1, stringContainer.getString("field_yellow_3"), Field.GUI_Type.Street_Yellow),
                new Field(stringContainer.getString("field_ship_4_title"), 0, stringContainer.getString("field_ship_4"), Field.GUI_Type.Shipping),
                new Field("C", 0, stringContainer.getString("field_chance"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_brown_1_title"), 1, stringContainer.getString("field_brown_1"), Field.GUI_Type.Street_Brown),
                new Field(stringContainer.getString("field_indkomstskat_title"), 0, stringContainer.getString("field_indkomstskat"), Field.GUI_Type.Tax),
                new Field(stringContainer.getString("field_brown_2_title"), 1, stringContainer.getString("field_brown_2"), Field.GUI_Type.Street_Brown),



        };
    }
}
