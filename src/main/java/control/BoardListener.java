package control;

import model.ChessManager;
import model.role.Role;
import model.role.RoleManager;
import view.board.Board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardListener extends MouseAdapter {

    private Board board;

    public BoardListener(Board board) {
        super();
        this.board = board;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(!GameControl.getInstance().isStart()){
            return ;
        }

        int row = board.xMappingToArrayRow(e.getX());
        int col = board.yMappingToArrayCol(e.getY());

        Role role = RoleManager.getInstance().getCurrentPlayer();
        ChessManager.getInstance().addChess(col,row,role.getColor());
        RoleManager.getInstance().nextPlayerRound();
    }

    public void mouseMoved(MouseEvent e) {
        board.setCursor(new Cursor(Cursor.HAND_CURSOR));  //光标为手掌
    }
}
