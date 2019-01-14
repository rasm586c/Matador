package Model.Fields;

import Model.GameState;
import com.sun.prism.RenderTarget;
import gui_fields.GUI_Field;

public class PropertyField extends Field {
    public PropertyField(String name, int value, String fieldText, GUI_Type type) {
        super(name, value, fieldText, type);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }

    public void x() {

    }

    private int calculateRent(GameState state) {
        if (owner != null && owner != state.getCurrentPlayer()) {
            // loop over alle fields
            boolean doubleRent = true;
            for (int i = 0; i < state.getBoard().getFields().length; i++) {
                // Hvis field == fieldType så ..
                if (fieldType.equals(state.getBoard().getFields()[i])) {
                    // Hvis owneren IKKE ejer dette felt og det er af samme type så er der IKKE dobbelt leje!
                    if (!owner.equals(state.getBoard().getFields()[i].owner)) {
                        doubleRent = false;
                        break;
                    }
                }
            }

            if (doubleRent) {
                return value * 2;
            }
        }

        return value;
    }
}
