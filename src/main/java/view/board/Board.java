package view.board;

import control.BoardListener;
import control.GameControl;
import model.ModelChangeListener;
import model.PieceColor;
import util.GoBangConstant;
import model.ChessManager;
import view.board.chess.BlackChess;
import view.board.chess.WhiteChess;

import javax.swing.*;
import java.awt.*;


public class Board extends JPanel implements ModelChangeListener{

    private static final long serialVersionUID = -7280514462376551938L;

    private final int MARGIN = 20;  //边距

    public Board() {
        BoardListener borderListener = new BoardListener(this);
        addMouseListener(borderListener);
        addMouseMotionListener(borderListener);

    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawGrid(graphics);
        drawStar(graphics);

    }

    private int getGridSpan() {
        return (getWidth() - MARGIN * 2) / (GoBangConstant.ROWS - 1);
    }

    private void drawGrid(Graphics graphics) {
        int span = getGridSpan();
        for (int i = 0; i < GoBangConstant.ROWS; i++) {
            graphics.drawLine(MARGIN, MARGIN + i * span, MARGIN + (GoBangConstant.COLS - 1) * span, MARGIN + i * span);
        }
        for (int j = 0; j < GoBangConstant.COLS; j++) {
            graphics.drawLine(MARGIN + j * span, MARGIN, MARGIN + j * span, MARGIN + (GoBangConstant.ROWS - 1) * span);
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

    //将数组的下标映射到实际的坐标
    public int rowMappingToPointX(int row) {
        return MARGIN + row * getGridSpan();

    }

    //将数组的下标映射到实际的坐标
    public int colMappingToPointY(int col) {
        return MARGIN + col * getGridSpan();
    }


    //将物理坐标映射为数组下标
    public int xMappingToArrayRow(int x) {
        return Math.round((float)(x - MARGIN) / getGridSpan());
    }

    //将物理坐标映射为数组下标
    public int yMappingToArrayCol(int y) {
       // RoundingMode.HALF_UP
        return Math.round((float)(y - MARGIN) / getGridSpan());
    }


    @Override
    public void addChess(int row, int col, PieceColor color) {
        int realX = colMappingToPointY(col);
        int realY = rowMappingToPointX(row);

        //TODO 换一种方式
        if(PieceColor.BLACK == color){
            new BlackChess(Color.black).drawChess(realX,realY,getGraphics());
        }else if(color == PieceColor.WHITE){
            new WhiteChess(Color.WHITE).drawChess(realX,realY,getGraphics());
        }
    }

    @Override
    public void clear() {
    }
}

