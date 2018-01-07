package view.chess;

import java.awt.*;

public class BlackChess extends Chess {

    public BlackChess(Point point, Color color) {
        super(point, color);
    }

    @Override
    protected RadialGradientPaint getPaint(int x, int y) {
        return new RadialGradientPaint(x,y, super.RADIUS, new float[]{0f, 1f}
                , new Color[]{Color.WHITE,Color.BLACK});
    }
}
