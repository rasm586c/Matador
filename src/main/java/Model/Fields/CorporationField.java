package Model.Fields;

import Model.GameState;

public class CorporationField extends Field {

    public CorporationField (String name, int value, String fieldtxt, GUI_Type type) {
        super(name, value, fieldtxt, type);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
