package Model;

import Model.Fields.*;

public class GameBoard {
    private LanguagePack stringContainer;
    private Field[] fields;

    public GameBoard(LanguagePack stringContainer) {
        this.stringContainer = stringContainer;
        fields = createFields();
    }

    /**
     * An array of the Gameboard fields. Model.Fields.Field variables include name, value, field text and field type.
     * Name and field text is found through our string container.
     */
    private Field[] createFields() {
        return new Field[]{
                new StartField(stringContainer.getString("field_start_title")),
                new PropertyField(stringContainer.getString("field_cyan_1_title"), 100,1000, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Cyan),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_cyan_2_title"), 150,1000, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Cyan),
                new TaxField(stringContainer.getString("field_indkomstskat_title"), 0,100, stringContainer.getString("field_indkomstskat")),
                new ShippingField(stringContainer.getString("field_ship_1_title"), 0,100, stringContainer.getString("field_ship_1")),
                new PropertyField(stringContainer.getString("field_pink_1_title"), 200,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Pink),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_pink_2_title"), 200,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Pink),
                new PropertyField(stringContainer.getString("field_pink_3_title"), 250, 100,stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Pink),
                new ParkingField(stringContainer.getString("field_jail_title"), stringContainer.getString("field_jail"), Field.GUI_Type.Jail),
                new PropertyField(stringContainer.getString("field_green_1_title"), 300,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Green),
                new BreweryField(stringContainer.getString("field_brewery_1_title"), 1,100, stringContainer.getString("field_brewery_1")),
                new PropertyField(stringContainer.getString("field_green_2_title"), 300,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Green),
                new PropertyField(stringContainer.getString("field_green_3_title"), 350,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Green),
                new ShippingField(stringContainer.getString("field_ship_2_title"), 0,100, stringContainer.getString("field_ship_2")),
                new PropertyField(stringContainer.getString("field_blue_1_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Blue),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_blue_2_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Blue),
                new PropertyField(stringContainer.getString("field_blue_3_title"), 1, 100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Blue),
                new ParkingField(stringContainer.getString("field_refuge_title"), stringContainer.getString("field_refuge"), Field.GUI_Type.Refuge),
                new PropertyField(stringContainer.getString("field_red_1_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Red),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_red_2_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Red),
                new PropertyField(stringContainer.getString("field_red_3_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Red),
                new ShippingField(stringContainer.getString("field_ship_3_title"), 0,100, stringContainer.getString("field_ship_3")),
                new PropertyField(stringContainer.getString("field_white_1_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_White),
                new PropertyField(stringContainer.getString("field_white_2_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_White),
                new BreweryField(stringContainer.getString("field_brewery_2_title"), 1,100, stringContainer.getString("field_brewery_2")),
                new PropertyField(stringContainer.getString("field_white_3_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_White),
                new GoToJailField(stringContainer.getString("field_jail_title"), stringContainer.getString("field_jail")),
                new PropertyField(stringContainer.getString("field_yellow_1_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Yellow),
                new PropertyField(stringContainer.getString("field_yellow_2_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Yellow),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_yellow_3_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Yellow),
                new ShippingField(stringContainer.getString("field_ship_4_title"), 0,100, stringContainer.getString("field_ship_4")),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_brown_1_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Brown),
                new TaxField(stringContainer.getString("field_indkomstskat_title"), 0,100, stringContainer.getString("field_indkomstskat")),
                new PropertyField(stringContainer.getString("field_brown_2_title"), 1,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Brown),



        };
    }

    public Field[] getFields() { return fields; }
}
