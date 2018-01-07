package view.chess;

import java.awt.*;

public class ChessManager {

    private static final int BLACK_CHESS = 1;
    private static final int WHITE_CHESS = 0;
    private static final int EMPTY_CHESS = -1;
    private final int cols;
    private final int rows;
    private int chessType;

    // 用于维护棋子的状态，0 代表 white chess ，1 代表 black chess 。 -1 代表 empty
    private int[][] chessesState;
    private Chess[][] chesses;

    public ChessManager(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;
        chessesState = new int[rows][cols];
        chesses = new Chess[rows][cols];

        initChess();
    }

    private void initChess() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chessesState[i][j] = EMPTY_CHESS;
            }
        }
    }

    public void drawAllChess(Graphics graphics) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (chessesState[i][j] == EMPTY_CHESS) {
                    continue;
                }

                chesses[i][j].drawChess(graphics);
            }
        }

    }

    public boolean isExist(int row, int col){
        if(chessesState[row][col] != EMPTY_CHESS){
            return true;
        }

        return false;
    }

    public void drawChess(int row,int col,Graphics graphics){
        chesses[row][col].drawChess(graphics);
    }

    public void addChess(int row,int col,int x ,int y){
        chessesState[row][col] = chessType;

        if(chessType == BLACK_CHESS){
            chesses[row][col] = new BlackChess(new Point(x,y),Color.BLACK);
            chessType = WHITE_CHESS;
        }
        else{
            chesses[row][col] = new WhiteChess(new Point(x,y),Color.BLACK);
            chessType = BLACK_CHESS;
        }
    }

    public boolean isWin(){
        //TODO 判断是否赢棋
        return false;
    }


}
