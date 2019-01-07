package control;

import view.board.Board;

import javax.swing.*;
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

    }

    public void mouseMoved(MouseEvent e) {
        board.setCursor(new Cursor(Cursor.HAND_CURSOR));  //光标为手掌
    }
}
