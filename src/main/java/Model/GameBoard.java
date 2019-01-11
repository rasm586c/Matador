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
                new Field(stringContainer.getString("field_start_titel"), 1, "Start", Field.GUI_Type.Start),
                new Field(stringContainer.getString("field_1_titel"), 1, stringContainer.getString("field_2"), Field.GUI_Type.Street_Cyan),
                new Field("C", 0, stringContainer.getString("field_prøv_lykken"), Field.GUI_Type.Chance),
                new Field(stringContainer.getString("field_2_titel"), 1, stringContainer.getString("field_2"), Field.GUI_Type.Street_Cyan),
                new Field(stringContainer.getString("field_indkomstskat"), 0, stringContainer.getString("field_2"), Field.GUI_Type.Tax),
        };
    }
}
