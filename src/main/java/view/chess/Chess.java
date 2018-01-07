package view.chess;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Chess {
    private final Point point;
    private final Color color;

    //棋子的半径不应该硬编码，需要根据整个panel的大小来变化
    public static final int RADIUS = 20; //半径

    public Chess(Point point,Color color) {
        this.point = point;
        this.color = color;
    }

    public void drawChess(Graphics graphics){
        ((Graphics2D) graphics).setPaint(getPaint(point.x,point.y));
        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

        Ellipse2D ellipse = new Ellipse2D.Float(point.x-20,point.y-20, 35, 35);
        ((Graphics2D)graphics).fill(ellipse);
    }

    abstract  protected RadialGradientPaint getPaint (int x, int y);

    public int getX(){
        return point.x;
    }

    public int getY(){
        return point.y;
    }



}
