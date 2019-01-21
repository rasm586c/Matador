package Model.Fields;

import Model.GameState;

public class TaxField extends Field {

    public TaxField(String name, String fieldText)
    {
        super(name,0,0,fieldText,GUI_Type.Tax);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
