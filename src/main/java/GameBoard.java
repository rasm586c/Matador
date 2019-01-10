import gui_fields.GUI_Field;

public class GameBoard {
    private LanguagePack stringContainer;

    public GameBoard(LanguagePack stringContainer) {
        this.stringContainer = stringContainer;
    }

    public GUI_Field[] getGuiFields() {
        Field[] fields = getFields();
        GUI_Field[] gui_fields = new GUI_Field[fields.length];
        for (int i = 0; i < fields.length; i++) {
            gui_fields[i] = fields[i].toGUI();
        }
        return gui_fields;
    }

    /**
     * An array of the Gameboard fields. Field variables include name, value, field text and field type.
     * Name and field text is found through our string container.
     */
    public Field[] getFields() {
        return new Field[]{
                new Field(stringContainer.getString("field_start_titel"), 1, "Start", Field.GUI_Type.Start),
                new Field(stringContainer.getString("field_burgerbar_titel"), 1, stringContainer.getString("field_burger_baren"), Field.GUI_Type.Street_Brown),
                new Field(stringContainer.getString("field_pizzaria_titel"), 1, stringContainer.getString("field_pizzariaet"), Field.GUI_Type.Street_Brown),
                new Field("C", 0, stringContainer.getString("field_prøv_lykken"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_candy_store_titel"), 1, stringContainer.getString("field_slik_butikken"), Field.GUI_Type.Street_Cyan),
                new Field(stringContainer.getString("field_ice_cream_titel"), 1, stringContainer.getString("field_is_kiosken"), Field.GUI_Type.Street_Cyan),
                new Field(stringContainer.getString("field_prison_titel"), 0, stringContainer.getString("field_fængsel"), Field.GUI_Type.Jail),
                new Field(stringContainer.getString("field_museum_titel"), 2, stringContainer.getString("field_museet"), Field.GUI_Type.Street_Purple),
                new Field(stringContainer.getString("field_library_titel"), 2, stringContainer.getString("field_biblioteket"), Field.GUI_Type.Street_Purple),
                new Field("C", 1, stringContainer.getString("field_prøv_lykken"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_skate_park_titel"), 2, stringContainer.getString("field_skate_parken"), Field.GUI_Type.Street_Orange),
                new Field(stringContainer.getString("field_pool_titel"), 2, stringContainer.getString("field_swimming_poolen"), Field.GUI_Type.Street_Orange),
                new Field(stringContainer.getString("field_parking_titel"), 0, stringContainer.getString("field_parkering"), Field.GUI_Type.Refuge),
                new Field(stringContainer.getString("field_game_hall_titel"), 3, stringContainer.getString("field_spille_hallen"), Field.GUI_Type.Street_Red),
                new Field(stringContainer.getString("field_cinema_titel"), 3, stringContainer.getString("field_kinoen"), Field.GUI_Type.Street_Red),
                new Field("C", 0, stringContainer.getString("field_prøv_lykken"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_toy_store_titel"), 3, stringContainer.getString("field_legetøjs_butikken"), Field.GUI_Type.Street_Yellow),
                new Field(stringContainer.getString("field_pet_store_titel"), 3, stringContainer.getString("field_dyre_butikken"), Field.GUI_Type.Street_Yellow),
                new Field(stringContainer.getString("field_go_to_prison_titel"), 1, stringContainer.getString("field_gå_fængsel"), Field.GUI_Type.Jail),
                new Field(stringContainer.getString("field_bowling_hall_titel"), 4, stringContainer.getString("field_bowling_hallen"), Field.GUI_Type.Street_Green),
                new Field(stringContainer.getString("field_zoo_titel"), 4, stringContainer.getString("field_zoo"), Field.GUI_Type.Street_Green),
                new Field("C", 0, stringContainer.getString("field_prøv_lykken"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_water_park_titel"), 5, stringContainer.getString("field_vandlandet"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),
                new Field(stringContainer.getString("field_beach_promenade"), 5, stringContainer.getString("field_strand_promenaden"), Field.GUI_Type.Street_Blue),

        };
    }

    /**
     * @return A list of gameboard fields.
     */
    @Override
    public String toString() {
        String out = "Fields:\n";
        for (int i = 0; i < getFields().length; i++) {
            if (i != getFields().length - 1) {
                out += getFields()[i].name + ",\n";
            } else {
                out += getFields()[i].name + ".";
            }
        }

        return out;
    }
}
