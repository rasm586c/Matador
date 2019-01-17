package Model;

import Model.Fields.*;

public class GameBoard {
    private LanguagePack stringContainer;
    private Field[] fields;

    public GameBoard(LanguagePack stringContainer) {
        this.stringContainer = stringContainer;
        fields = createFields();
        setRents();
    }

    private void setRents() {
        setRent(fields[1], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[3], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[5], new int[] { 1000, 2000, 3000, 4000 }); // Ship
        setRent(fields[6], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[8], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[9], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[11], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[12], new int[] { 1000, 2000, 3000, 4000 }); // Brewery
        setRent(fields[13], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[14], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[15], new int[] { 1000, 2000, 3000, 4000 }); // Ship
        setRent(fields[16], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[18], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[19], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[21], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[23], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[24], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[25], new int[] { 1000, 2000, 3000, 4000 }); // Ship
        setRent(fields[26], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[27], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[28], new int[] { 1000, 2000, 3000, 4000 }); // Brewery
        setRent(fields[29], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[31], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[32], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[34], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[35], new int[] { 1000, 2000, 3000, 4000 }); // Ship
        setRent(fields[37], new int[] { 1000, 2000, 3000, 4000 });
        setRent(fields[39], new int[] { 1000, 2000, 3000, 4000 });
    }

    private void setRent(Field field, int[] prices) {
        if (field instanceof OwnableField) {
            OwnableField ownableField = (OwnableField)field;
            ownableField.setRentPrices(prices);
        }
    }

    /**
     * An array of the Gameboard fields. Model.Fields.Field variables include name, value, field text and field type.
     * Name and field text is found through our string container.
     */
    private Field[] createFields() {
        return new Field[]{
                new StartField(stringContainer.getString("field_start_title")),
                new PropertyField(stringContainer.getString("field_cyan_1_title"), 1200,1000, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Cyan),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_cyan_2_title"), 1200,1000, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Cyan),
                new TaxField(stringContainer.getString("field_indkomstskat_title"), 0,100, stringContainer.getString("field_indkomstskat")),
                new ShippingField(stringContainer.getString("field_ship_1_title"), 4000,500, stringContainer.getString("field_ship_rent")),
                new PropertyField(stringContainer.getString("field_pink_1_title"), 2000,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Pink),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_pink_2_title"), 2000,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Pink),
                new PropertyField(stringContainer.getString("field_pink_3_title"), 2400, 100,stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Pink),
                new ParkingField(stringContainer.getString("field_jail_title"), stringContainer.getString("field_jail"), Field.GUI_Type.Jail),
                new PropertyField(stringContainer.getString("field_green_1_title"), 2800,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Green),
                new BreweryField(stringContainer.getString("field_brewery_1_title"), 3000,100, stringContainer.getString("field_brewery_1")),
                new PropertyField(stringContainer.getString("field_green_2_title"), 2800,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Green),
                new PropertyField(stringContainer.getString("field_green_3_title"), 3200,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Green),
                new ShippingField(stringContainer.getString("field_ship_2_title"), 4000,500, stringContainer.getString("field_ship_rent")),
                new PropertyField(stringContainer.getString("field_blue_1_title"), 3600,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Blue),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_blue_2_title"), 3600,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Blue),
                new PropertyField(stringContainer.getString("field_blue_3_title"), 4000, 100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Blue),
                new ParkingField(stringContainer.getString("field_refuge_title"), stringContainer.getString("field_refuge"), Field.GUI_Type.Refuge),
                new PropertyField(stringContainer.getString("field_red_1_title"), 4400,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Red),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_red_2_title"), 4400,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Red),
                new PropertyField(stringContainer.getString("field_red_3_title"), 4800,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Red),
                new ShippingField(stringContainer.getString("field_ship_3_title"), 4000,500, stringContainer.getString("field_ship_rent")),
                new PropertyField(stringContainer.getString("field_white_1_title"), 5200,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_White),
                new PropertyField(stringContainer.getString("field_white_2_title"), 5200,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_White),
                new BreweryField(stringContainer.getString("field_brewery_2_title"), 3000,100, stringContainer.getString("field_brewery_2")),
                new PropertyField(stringContainer.getString("field_white_3_title"), 5600,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_White),
                new GoToJailField(stringContainer.getString("field_jail_title"), stringContainer.getString("field_jail")),
                new PropertyField(stringContainer.getString("field_yellow_1_title"), 6000,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Yellow),
                new PropertyField(stringContainer.getString("field_yellow_2_title"), 6000,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Yellow),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_yellow_3_title"), 6400,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Yellow),
                new ShippingField(stringContainer.getString("field_ship_4_title"), 4000,500, stringContainer.getString("field_ship_rent")),
                new ChanceField(stringContainer.getString("field_chance")),
                new PropertyField(stringContainer.getString("field_brown_1_title"), 7000,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Brown),
                new TaxField(stringContainer.getString("field_indkomstskat_title"), 0,100, stringContainer.getString("field_indkomstskat")),
                new PropertyField(stringContainer.getString("field_brown_2_title"), 8000,100, stringContainer.getString("field_property_rent"), Field.GUI_Type.Street_Brown),



        };
    }

    public Field[] getFields() { return fields; }
}
