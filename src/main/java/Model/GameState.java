package Model;

public class GameState {
    private Player[] players;
    private PlayerTurn turn;
    private Player currentPlayer;
    private GameBoard board;

    public Player getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }

    public void setPlayers(Player[] players) { this.players = players; }
    public Player[] getPlayers() { return players; }

    public void setTurn(PlayerTurn turn) { this.turn = turn; }
    public PlayerTurn getTurn() { return turn; }

    public GameBoard getBoard() { return board; }
    public void setBoard(GameBoard board) {  this.board = board; }
}
