package Model.Fields;

import Model.GameState;

public class ParkingField extends Field {

    public ParkingField(String name, String fieldtxt, GUI_Type type){
        super(name,0,fieldtxt,type);
    }


    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
