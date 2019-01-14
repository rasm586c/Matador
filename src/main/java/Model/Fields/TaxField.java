package Model.Fields;

import Model.GameState;

public class TaxField extends Field {

    public TaxField(String name, int value, String fieldtxt){
        super(name,value,fieldtxt,GUI_Type.Tax);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
