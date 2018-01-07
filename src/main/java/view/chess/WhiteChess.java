package view.chess;

import java.awt.*;

public class WhiteChess extends Chess {
    public WhiteChess(Color color) {
        super(color);
    }

    @Override
    protected RadialGradientPaint getPaint(int x, int y) {
        return new RadialGradientPaint(x,y, super.RADIUS, new float[]{0f, 1f}
                , new Color[]{Color.BLACK,Color.WHITE});
    }


}
