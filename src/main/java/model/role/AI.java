package model.role;

import model.ChessManager;
import model.PieceColor;
import model.algorithms.Minimax;
import model.algorithms.Step;

public class AI extends Role {

    private final int depth = 2;

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
        Role role = RoleManager.getInstance().getCurrentPlayer();
        Step step = new Minimax().go(depth,Integer.MIN_VALUE,Integer.MAX_VALUE,role,node);
        System.out.println("step" + step.getScore());
        ChessManager.getInstance().addChess(step.getX(),step.getY(),role.getColor());
        RoleManager.getInstance().nextPlayerRound();
    }
}
