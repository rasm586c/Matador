package Model;

public class Player {
    private final String name;
    private final PlayerType playerType;

    private int position;

    Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;

        position = 0;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() { return  playerType; }

    public int getPosition() {return position;}
    public void setPosition(int position) { this.position = position; }

    public Player clone() {
        return new Player(name, playerType);
    }
}
