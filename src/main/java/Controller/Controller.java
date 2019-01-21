package Controller;
import Model.GameState;
import View.View;

public abstract class Controller {
    final protected View view;

    public Controller(View view) {
        this.view = view;
    }

    public ControllerChoice[] getControllerChoices(GameState state) {
        return new ControllerChoice[] {};
    }
}

