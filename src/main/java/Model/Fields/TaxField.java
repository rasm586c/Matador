package Model.Fields;

import Model.GameState;

public class TaxField extends Field {

    public TaxField(String name, int value,int rent, String fieldText)
    {
        super(name,value,rent,fieldText,GUI_Type.Tax);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
