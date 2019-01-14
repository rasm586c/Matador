package Model.Fields;

import Model.GameState;

public class PropertyField extends Field {
    public PropertyField(String name, int value, String fieldText, GUI_Type type) {
        super(name, value, fieldText, type);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);

        // Du er landed på et property field! Gør noget!

    }
}
