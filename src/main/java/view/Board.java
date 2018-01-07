package view;

import view.chess.Chess;
import view.chess.ChessManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        drawAllChess(graphics);

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

    private void drawAllChess(Graphics graphics){

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (!chessMgr.isExist(i,j)) {
                        continue;
                    }

                    chessMgr.drawChess(i,j,rowMappingToPointX(i),colMappingToPointY(j),graphics);
                }
            }

    }


    //将数组的下标映射到实际的坐标
    private int rowMappingToPointX(int row){
       return MARGIN + row * getGridSpan();

    }

    //将数组的下标映射到实际的坐标
    private int colMappingToPointY(int col){
        return  MARGIN + col * getGridSpan();
    }


    //将物理坐标映射为数组下标
    private Point mappingToArray(int x,int y){
        int row = (x - MARGIN) / getGridSpan();
        int col = (y - MARGIN) / getGridSpan();
        return new Point(row,col);
    }


    class BoardListener extends MouseAdapter{

        public BoardListener() {
            super();
        }


        @Override
        public void mousePressed(MouseEvent e){
           Point point =  mappingToArray(e.getX(),e.getY());
            System.out.println(point.x);
            System.out.println(point.y);
           if(chessMgr.isExist(point.x,point.y)){
               //TODO 需要给出提示和声音
               return;
           }


            chessMgr.addChess(point.x,point.y);
            chessMgr.drawChess(point.x,point.y,e.getX(),e.getY(),getGraphics());
            if(chessMgr.isWin(point.x,point.y)){
                System.out.println("Win");
            }
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

