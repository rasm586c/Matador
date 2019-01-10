package Controller;
import View.View;

public abstract class Controller {
    final protected View view;

    public Controller(View view) {
        this.view = view;
    }
}
