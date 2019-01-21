package Model.Fields;

import Model.GameState;

public class LoanField extends Field {

    public LoanField(String name, String fieldText) {
        super(name,0,0,fieldText,GUI_Type.Loan);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
