package view.board.chess;

import java.awt.*;

public class ChessManager {

    private static final int BLACK_CHESS = 1;
    private static final int WHITE_CHESS = 0;
    private static final int EMPTY_CHESS = -1;
    private final int cols;
    private final int rows;
    private int chessType;

    // 用于维护棋子的状态，0 代表 white chess ，1 代表 black chess 。 -1 代表 empty
    private int[][] state;
    private Chess[][] chesses;

    public ChessManager(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;
        state = new int[rows][cols];
        chesses = new Chess[rows][cols];

        initChess();
    }

    public void initChess() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                state[i][j] = EMPTY_CHESS;
            }
        }

        chesses = new Chess[rows][cols];
        chessType = BLACK_CHESS;
    }



    public boolean isExist(int row, int col){
        if(state[row][col] != EMPTY_CHESS){
            return true;
        }

        return false;
    }

    public void drawChess(int row,int col,int x ,int y,Graphics graphics){
        chesses[row][col].drawChess(x,y,graphics);
    }

    public void addChess(int row,int col){
        state[row][col] = chessType;

        if(chessType == BLACK_CHESS){
            chesses[row][col] = new BlackChess(Color.BLACK);
            chessType = WHITE_CHESS;
        }
        else{
            chesses[row][col] = new WhiteChess(Color.BLACK);
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

            if(state[i][col] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row + 1; i < this.rows; i++){
            if(state[i][col] != state[row][col]){
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
            if(state[row][i] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = col + 1; i < this.cols; i++){
            if(state[row][i] != state[row][col]){
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
            if(state[i][j] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row +1,j = col + 1; i< rows && j < cols;i++,j++){
            if(state[i][j] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        return false;
    }

    private boolean rightOblique(int row,int col){
        int chessNum = 1;
        for(int i = row + 1,j = col -1; i < rows && j >=0;i++,j--){
            if(state[i][j] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row -1,j = col + 1; i >= 0  && j < cols;i--,j++){
            if(state[i][j] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        return false;
    }

    public String getCurrentChess(){
        if(chessType == BLACK_CHESS){
            return "White Chess";
        }else{
            return "Black Chess";
        }
    }

}
