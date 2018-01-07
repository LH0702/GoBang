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

    public boolean isWin(int row,int col){

       if(horizontalJudget(row,col)){
           return true;
       }

       if(verticalJudget(row,col)){
           return true;
       }

       if(leftOblique(row,col)){
           return true;
       }

       if(rightOblique(row,col)){
           return true;
       }

        return false;
    }

    // 横向判断
    private boolean horizontalJudget(int row,int col){
        int chessNum = 1;

        for(int i = row-1; i >= 0 ; i--){

            if(chessesState[i][col] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row + 1; i < this.rows; i++){
            if(chessesState[i][col] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        return false;
    }

    //纵向判断

    private boolean verticalJudget(int row,int col){
        int chessNum = 1;

        for(int i = col-1; i >= 0 ; i--){
            if(chessesState[row][i] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = col + 1; i < this.cols; i++){
            if(chessesState[row][i] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        return false;
    }

    private boolean leftOblique(int row,int col){
        int chessNum = 1;
        for(int i = row -1,j = col -1; i>= 0 && j >=0;i--,j--){
            if(chessesState[i][j] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row +1,j = col + 1; i< rows && j < cols;i++,j++){
            if(chessesState[i][j] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        return true;
    }

    private boolean rightOblique(int row,int col){
        int chessNum = 1;
        for(int i = row + 1,j = col -1; i < rows && j >=0;i++,j--){
            if(chessesState[i][j] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row -1,j = col + 1; i >= 0  && j < cols;i--,j++){
            if(chessesState[i][j] != chessesState[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        return true;
    }

}
