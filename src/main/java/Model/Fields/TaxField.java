package Model.Fields;

import Model.GameState;

public class TaxField extends Field {

    public TaxField(String name, int value, String fieldText)
    {
        super(name,value,fieldText,GUI_Type.Tax);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
