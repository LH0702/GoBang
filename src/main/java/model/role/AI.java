package model.role;

import model.ChessManager;
import model.PieceColor;

public class AI extends Role {

    public AI(PieceColor color){
        super(color);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void generateNextStep() {
        PieceColor[][] node = ChessManager.getInstance().getState();

    }
}
