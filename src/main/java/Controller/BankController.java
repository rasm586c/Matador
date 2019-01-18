package Controller;

import Model.*;
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

    public int getMoney(Player player) {
        Account account = findAccountByPlayer(player);
        return account.getBalance();
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

        if (transaction.getTransactionType() == Transaction.TransactionType.PurchaseProperty) {
            if (account.getBalance() >= transaction.getAmount()) {
                transaction.setApproved(true);
            } else {
                transaction.setApproved(false);
            }

            if (transaction.isApproved()) {
                transaction.getField().setOwner(state.getCurrentPlayer());
                view.updateOwner(state.getCurrentPlayer(), state.getCurrentPlayer().getPositionClamped());

                withdrawMoney(state.getCurrentPlayer(), transaction.getAmount());
                updateBalances();
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.OutOfJail) {
            if (account.getBalance() >= transaction.getAmount()) {
                withdrawMoney(transaction.getPlayer(), transaction.getAmount());
                transaction.getPlayer().setJailedTurns(0);
                transaction.setApproved(true);
            } else {
                transaction.setApproved(false);

                // TODO: Fix game strings
                view.print("Øv! Du har ikke råd.");
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.PayTax) {
            if (account.getBalance() >= 40000) {
                withdrawMoney(transaction.getPlayer(), 4000);
                transaction.setApproved(true);
                view.print("Du har rigtig mange penge, så du betaler 4.000");
            } else {
                transaction.setApproved(true);
                int withdrawAmount = getMoney(transaction.getPlayer())*10/100;
                withdrawMoney(transaction.getPlayer(), withdrawAmount);

                // TODO: Fix game strings
                view.print("Du har ikke helt så mange penge, du betaler bare 10% af dine penge.");
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.PayToBank) {
            if (account.getBalance() >= transaction.getAmount()) {
                withdrawMoney(transaction.getPlayer(), transaction.getAmount());
                transaction.setApproved(true);
            } else {
                transaction.setApproved(false);
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.ToPlayer) {
            if (account.getBalance() >= transaction.getAmount()) {
                // Transfer amount from player to target
                withdrawMoney(transaction.getPlayer(), transaction.getAmount());
                addMoney(transaction.getTarget(), transaction.getAmount());
                transaction.setApproved(true);
            } else {
                transaction.setApproved(false);
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
            }
        }

        if (transaction.getTransactionType() == Transaction.TransactionType.PayTax) {
            if (account.getBalance() >= transaction.getAmount()) {
                withdrawMoney(transaction.getPlayer(), transaction.getAmount());
                transaction.setApproved(true);

                view.print("Du skal betale din studiegæld!");

            }
        }
    }
}
