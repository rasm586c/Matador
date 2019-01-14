package Model;

import Model.Fields.Field;

public class Transaction {
    private boolean approved = false;
    private Player player;
    private Field field;

    public Transaction(Player player, Field field) {
        this.player = player;
        this.field = field;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) { this.player = player; }
}
