package model;

public class Role {

    private Role opponent ;

    private boolean maximizingPlayer ;

    private final PieceColor color;

    public Role(PieceColor color){
        this.color = color;
    }

    public PieceColor getColor(){
        return this.color;
    }

    public Role getOpponent() {
        return opponent;
    }

    public void setOpponent(Role opponent) {
        this.opponent = opponent;
    }

    public boolean isMaximizingPlayer(){
        return this.maximizingPlayer;
    }

    public void setMaximizingPlayer(){
        this.maximizingPlayer = true;
    }

    public void setminimizingPlayer(){
        this.maximizingPlayer = false;
    }


}
