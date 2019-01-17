package Controller;

import Model.*;
import Model.Fields.Field;
import View.View;

public class BankController extends Controller {
    Account[] accounts;

    public BankController(View view, Player[] players) {
        super(view);
        accounts = createAccounts(players);
        updateBalances();
    }

    private Account[] createAccounts(Player[] players) {
        Account[] accounts = new Account[players.length];

        for (int i = 0; i < players.length; i++) {
            Account account = new Account(players[i], 30000);
            accounts[i] = account;
        }

        return accounts;
    }

    public void addMoney(Player player, int money) {
        Account account = findAccountByPlayer(player);
        account.setBalance(account.getBalance() + money);

        updateBalances();
    }

    public void withdrawMoney(Player player, int money) {
        Account account = findAccountByPlayer(player);
        account.setBalance(account.getBalance() - money);

        updateBalances();
    }

    private Account findAccountByPlayer(Player player) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].owner == player) { return accounts[i]; }
        }
        return null;
    }

    private void updateBalances() {
        for (int i = 0; i < accounts.length; i++) {
            view.setPlayerBalance(accounts[i].owner, accounts[i].getBalance());
        }
    }

    public void processTransaction(Transaction transaction, GameState state) {
        Account account = findAccountByPlayer(transaction.getPlayer());
        Field landedOn = state.getBoard().getFields()[transaction.getPlayer().getPositionClamped()];

        if (transaction.getTransactionType() == Transaction.TransactionType.PurchaseProperty) {
            if (account.getBalance() >= transaction.getAmount()) {
                transaction.setApproved(true);
            } else {
                transaction.setApproved(false);
            }

            if (transaction.isApproved()) {
                landedOn.setOwner(state.getCurrentPlayer());
                view.updateOwner(state.getCurrentPlayer(), state.getCurrentPlayer().getPositionClamped());

                withdrawMoney(state.getCurrentPlayer(), transaction.getAmount());
                updateBalances();
            } else {
                //TODO: Mere logik
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.ToPlayer) {
            if (account.getBalance() >= transaction.getAmount()) {
                // Transfer amount from player to target
                withdrawMoney(transaction.getPlayer(), transaction.getAmount());
                addMoney(transaction.getTarget(), transaction.getAmount());
                transaction.setApproved(true);
                view.print("Du " + transaction.getPlayer().getName() + " har givet " + transaction.getAmount() + " til " + transaction.getTarget().getName());
            } else {
                transaction.setApproved(false);
                // TODO: Har ikke nok penge, men du er landet på grund!
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.PurchaseHouse) {
            if (account.getBalance() >= transaction.getAmount()) {
                withdrawMoney(transaction.getPlayer(), transaction.getAmount());
                transaction.setApproved(true);

                view.print("Du har købt et hus på " + transaction.getField().name);
                transaction.getField().setHouseCounter(transaction.getField().getHouseCounter() + 1);
                int fieldPosition = -1;
                for (int i = 0; i < state.getBoard().getFields().length; i++) {
                    if (state.getBoard().getFields()[i] == transaction.getField()) {
                        fieldPosition = i;
                    }
                }

                view.updateHouse(fieldPosition, transaction.getField().getHouseCounter());
            }
            else {
                transaction.setApproved(false);
                view.print("Øv! Du har ikke råd til dette hus!!");
            }
        }
    }
}
