package Model;

public class Account {
    public final Player owner;
    int balance;

    public Account(Player owner, int startBalance) {
        this.owner = owner;
        balance = startBalance;
    }

    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
}


