package Model;

import java.awt.*;

public class Player {
    static final int BOARD_SIZE = 40;

    private final String name;
    private final PlayerType playerType;

    public final Color color;

    private int position;
    private int jailedTurns;

    public Player(String name, PlayerType playerType, Color color) {
        this.name = name;
        this.playerType = playerType;
        this.color = color;

        jailedTurns = 0;
        position = 0;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() { return  playerType; }

    public int getPosition() {return position;}
    public void setPosition(int position) { this.position = position; }

    public int getPositionClamped() { return clampPosition(position); }

    public Player clone() {
        return new Player(name, playerType, color);
    }

    public int getJailedTurns() { return jailedTurns; }
    public void setJailedTurns(int jailedTurns) { this.jailedTurns = jailedTurns; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) { return ((Player)obj).getName().equals(getName()); }
        return false;
    }

    /**
     * This function recursively calls itself until it ensures the input value is between 0 and BOARD_SIZE (I.e. clamps a position to the board)
     * @param position
     * @return
     */
    public static int clampPosition(int position) {
        if (position < 0) { return clampPosition(position + BOARD_SIZE); }
        if (position < BOARD_SIZE) return position;
        return clampPosition(position - BOARD_SIZE);
    }
}
