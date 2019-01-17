package Model;

import Model.Fields.Field;

public class Transaction {
    private boolean approved = false;
    private Player player;
    private Player target;
    private Field field;

    private int amount;
    private TransactionType transactionType;

    public Transaction(Player player, Field field, int amount, TransactionType transactionType) {
        this.player = player;
        this.field = field;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public void setAmount(int amount) { this.amount = amount; }
    public int getAmount() { return amount; }

    public Player getTarget() { return target; }
    public void setTarget(Player target) { this.target = target; }

    public TransactionType getTransactionType() { return transactionType; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }

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

    public void setField(Field field) { this.field = field; }
    public Field getField() { return field; }

    public enum TransactionType {
        PurchaseProperty,
        PurchaseHouse,
        ToPlayer
    }
}
