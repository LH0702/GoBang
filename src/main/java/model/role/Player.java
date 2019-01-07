package model.role;

import model.PieceColor;

public class Player extends Role {

    public Player(PieceColor color){
        super(color);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public void generateNextStep() {
        //TODO
    }
}
