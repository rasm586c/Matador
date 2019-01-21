package Model.Fields;

import Model.GameState;

public interface OwnableField {
    int calculateRent(GameState state);

    int[] getRentPrices();
    void setRentPrices(int[] prices);
}
