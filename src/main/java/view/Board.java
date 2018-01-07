package view;

import view.chess.Chess;
import view.chess.ChessManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;


public class Board extends JPanel {

    private static final long serialVersionUID = -7280514462376551938L;

    private  final int MARGIN = 20;  //边距
    private  final int ROWS = 15;  //棋盘行数
    private  final int COLS = 15;  //棋盘列数
    private ChessManager chessMgr;

    public Board(){
        BoardListener borderListener = new BoardListener();
        addMouseListener(borderListener);
        addMouseMotionListener(borderListener);
        chessMgr = new ChessManager(ROWS,COLS);
        setBackground(Color.cyan);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawGrid(graphics);
        drawStar(graphics);
        chessMgr.drawAllChess(graphics);

    }

    private int getGridSpan(){
        return (getWidth()- MARGIN*2) / (ROWS-1);
    }

    private void drawGrid(Graphics graphics){
        int span = getGridSpan();
        for (int i = 0; i < ROWS; i++) {
            graphics.drawLine(MARGIN, MARGIN + i * span, MARGIN + (COLS-1) * span, MARGIN + i * span);
        }
        for (int j = 0; j < COLS; j++) {
            graphics.drawLine(MARGIN + j * span, MARGIN, MARGIN + j * span, MARGIN + (ROWS-1) * span);
        }
    }

    private void drawStar(Graphics graphics){
        int span = getGridSpan();
        int width = 10;
        int height = 10;

        graphics.fillArc(MARGIN + 3 * span - width/2,MARGIN + 3 * span - width/2 ,width,height,0,360);
        graphics.fillArc(MARGIN + 11 * span - width/2,MARGIN + 11 * span - width/2 ,width,height,0,360);
        graphics.fillArc(MARGIN + 7 * span - width/2,MARGIN + 7 * span - width/2 ,width,height,0,360);
        graphics.fillArc(MARGIN + 3 * span - width/2,MARGIN + 11 * span - width/2 ,width,height,0,360);
        graphics.fillArc(MARGIN + 11 * span - width/2,MARGIN + 3 * span - width/2 ,width,height,0,360);
    }


    //将数组的下标映射到实际的坐标
    private Point mappingToPoint(int row,int col){

        int x = MARGIN + row * getGridSpan();
        int y = MARGIN + col * getGridSpan();
        return new Point(x,y);
    }

    //将物理坐标映射为数组下标
    private Point mappingToArray(int row,int col){
         return null;
    }


    class BoardListener extends MouseAdapter{

        public BoardListener() {
            super();
        }


        @Override
        public void mousePressed(MouseEvent e){
            mappingToArray(e.getX(),e.getY());
        }

        public void mouseMoved(MouseEvent e) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));  //光标为手掌
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test draw a board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setBackground(Color.pink);
        frame.add(new Board());

    }
}

