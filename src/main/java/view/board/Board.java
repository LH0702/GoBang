package view.board;

import control.GameControl;
import view.board.chess.ChessManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Board extends JPanel {

    private static final long serialVersionUID = -7280514462376551938L;

    private final int MARGIN = 20;  //边距
    private final int ROWS = 15;  //棋盘行数
    private final int COLS = 15;  //棋盘列数
    private ChessManager chessMgr;


    public Board() {
        BoardListener borderListener = new BoardListener();
        addMouseListener(borderListener);
        addMouseMotionListener(borderListener);
        chessMgr = new ChessManager(ROWS, COLS);
    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawGrid(graphics);
        drawStar(graphics);
        drawAllChess(graphics);

    }

    private int getGridSpan() {
        return (getWidth() - MARGIN * 2) / (ROWS - 1);
    }

    private void drawGrid(Graphics graphics) {
        int span = getGridSpan();
        for (int i = 0; i < ROWS; i++) {
            graphics.drawLine(MARGIN, MARGIN + i * span, MARGIN + (COLS - 1) * span, MARGIN + i * span);
        }
        for (int j = 0; j < COLS; j++) {
            graphics.drawLine(MARGIN + j * span, MARGIN, MARGIN + j * span, MARGIN + (ROWS - 1) * span);
        }
    }

    private void drawStar(Graphics graphics) {
        int span = getGridSpan();
        int width = 10;
        int height = 10;

        graphics.fillArc(MARGIN + 3 * span - width / 2, MARGIN + 3 * span - width / 2, width, height, 0, 360);
        graphics.fillArc(MARGIN + 11 * span - width / 2, MARGIN + 11 * span - width / 2, width, height, 0, 360);
        graphics.fillArc(MARGIN + 7 * span - width / 2, MARGIN + 7 * span - width / 2, width, height, 0, 360);
        graphics.fillArc(MARGIN + 3 * span - width / 2, MARGIN + 11 * span - width / 2, width, height, 0, 360);
        graphics.fillArc(MARGIN + 11 * span - width / 2, MARGIN + 3 * span - width / 2, width, height, 0, 360);
    }

    private void drawAllChess(Graphics graphics) {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!chessMgr.isExist(i, j)) {
                    continue;
                }

                chessMgr.drawChess(i, j, rowMappingToPointX(i), colMappingToPointY(j), graphics);
            }
        }

    }


    //将数组的下标映射到实际的坐标
    private int rowMappingToPointX(int row) {
        return MARGIN + row * getGridSpan();

    }

    //将数组的下标映射到实际的坐标
    private int colMappingToPointY(int col) {
        return MARGIN + col * getGridSpan();
    }


    //将物理坐标映射为数组下标
    private int xMappingToArrayRow(int x) {
        return Math.round((float)(x - MARGIN) / getGridSpan());
    }

    //将物理坐标映射为数组下标
    private int yMappingToArrayCol(int y) {
       // RoundingMode.HALF_UP
        return Math.round((float)(y - MARGIN) / getGridSpan());
    }


    class BoardListener extends MouseAdapter {

        public BoardListener() {
            super();
        }


        @Override
        public void mousePressed(MouseEvent e) {
            if(!GameControl.getInstance().isStart()){
                return ;
            }

            int row = xMappingToArrayRow(e.getX());
            int col = yMappingToArrayCol(e.getY());

            if (chessMgr.isExist(row, col)) {
                //TODO 需要给出提示和声音
                return;
            }

            chessMgr.addChess(row, col);
            chessMgr.drawChess(row, col, rowMappingToPointX(row), colMappingToPointY(col), getGraphics());

            if (chessMgr.isWin(row, col)) {
                JOptionPane.showMessageDialog(null, chessMgr.getCurrentChess() + " is Win", "对局结束",
                        JOptionPane.YES_NO_OPTION);
                System.out.println("Win");
                GameControl.getInstance().endGame();
            }

        }

        public void mouseMoved(MouseEvent e) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));  //光标为手掌
        }
    }

}
