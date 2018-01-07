package view.chess;

import java.awt.*;

public class BlackChess extends Chess {

    public BlackChess(Color color) {
        super(color);
    }

    @Override
    protected RadialGradientPaint getPaint(int x, int y) {
        return new RadialGradientPaint(x-RADIUS/2,y-RADIUS/2, super.RADIUS, new float[]{0f, 1f}
                , new Color[]{Color.WHITE,Color.BLACK});
    }
}
