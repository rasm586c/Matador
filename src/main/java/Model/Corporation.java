package Model;

public class Corporation extends Field {

    public Corporation (String name, int value, String fieldtxt, GUI_Type type) {
        super(name, value, fieldtxt, type);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);
    }
}
