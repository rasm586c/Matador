package Model.Fields;

import Model.GameState;

public class TaxField extends Field {

    public TaxField(String name, int value,int rent, int mortgage, String fieldText)
    {
        super(name,value,rent,mortgage,fieldText,GUI_Type.Tax);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
