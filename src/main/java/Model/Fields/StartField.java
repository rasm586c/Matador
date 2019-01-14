package Model.Fields;

import Model.GameState;

public class StartField extends Field {
    public StartField(String name) {
        super(name, 1, "Start", Field.GUI_Type.Start);
    }
}
