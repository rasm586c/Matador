package Model;

public class GameState {
    private Player[] players;
    private PlayerTurn turn;
    private Player currentPlayer;

    public Player getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }

    public void setPlayers(Player[] players) { this.players = players; }
    public Player[] getPlayers() { return players; }

    public void setTurn(PlayerTurn turn) { this.turn = turn; }
    public PlayerTurn getTurn() { return turn; }
}
