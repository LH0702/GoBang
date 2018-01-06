package view;

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
    private  int[][] chesses = new int[ROWS][COLS];

    public Board(){
        BoardListener borderListener = new BoardListener();
        addMouseListener(borderListener);
        addMouseMotionListener(borderListener);
        setBackground(Color.cyan);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawGrid(graphics);
        drawStar(graphics);

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

    class BoardListener extends MouseAdapter{

        public BoardListener() {
            super();
        }


        @Override
        public void mousePressed(MouseEvent e){
            RadialGradientPaint paint = new RadialGradientPaint(e.getX(),e.getY(), 20, new float[]{0f, 1f}
                    , new Color[]{Color.WHITE, Color.BLACK});

            ((Graphics2D) getGraphics()).setPaint(paint);
            ((Graphics2D) getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) getGraphics()).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            Ellipse2D ellipse = new Ellipse2D.Float(e.getX()-20,e.getY()-20, 35, 35);
            ((Graphics2D)getGraphics()).fill(ellipse);
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

