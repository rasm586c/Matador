package Model.Fields;

import Model.GameState;

public class BreweryField extends Field {

    public BreweryField(String name, int value, String fieldtxt) {
        super(name, value, fieldtxt, GUI_Type.Brewery);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
