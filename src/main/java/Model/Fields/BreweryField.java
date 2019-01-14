package Model.Fields;

import Model.GameState;

public class BreweryField extends Field implements OwnableField {

    public BreweryField(String name, int value, String fieldText) {
        super(name, value, fieldText, GUI_Type.Brewery);
    }

    // TODO: Fix gamestring tekst
    public String getPurchaseText() {
        return "Bryggeri til salg! Vil du købe? Den koster " + value;
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }

    public int calculateRent(GameState state) {
        return value;
    }
}
