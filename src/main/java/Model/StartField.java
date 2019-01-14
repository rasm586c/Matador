package Model;

public class StartField extends Field {
    public StartField(String name) {
        super(name, 1, "Start", Field.GUI_Type.Start);
    }

    @Override
    public void onFieldLand(GameState state) {
        super.onFieldLand(state);

        // Du er landed på start felt.. Gør noget med game state

    }
}
