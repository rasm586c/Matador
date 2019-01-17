package Model.Fields;

import Model.GameState;

public class ParkingField extends Field {
    public ParkingField(String name, String fieldText, GUI_Type type){
        super(name,0,0,fieldText,type);
    }


    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
