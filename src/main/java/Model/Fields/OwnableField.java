package Model.Fields;

import Model.GameState;

public interface OwnableField {
    String getPurchaseText();
    int calculateRent(GameState state);

}
