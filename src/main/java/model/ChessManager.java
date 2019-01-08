package model;

import util.GoBangConstant;

import java.util.ArrayList;
import java.util.List;


public class ChessManager {

    // 用于维护棋子的状态，0 代表 white chess ，1 代表 black chess 。 -1 代表 empty
    private PieceColor[][] state;

    private List<ModelChangeListener> listeners = new ArrayList<>();

    private static final ChessManager instance = new ChessManager();

    public static ChessManager getInstance(){
        return instance;
    }

    private ChessManager(){

    }

    public void init(){
        initChess();
    }

    private void initChess() {
        state = new PieceColor[GoBangConstant.ROWS][GoBangConstant.COLS];
        for (int i = 0; i < GoBangConstant.ROWS; i++) {
            for (int j = 0; j < GoBangConstant.COLS; j++) {
                state[i][j] = PieceColor.BLANK;
            }
        }
    }



    public boolean isExist(int row, int col){
        if(state[row][col] != PieceColor.BLANK){
            return true;
        }

        return false;
    }

    public boolean addChess(int row,int col,PieceColor color){
        if(isExist(row,col)){
            return false;
        }

        this.state[row][col] = color;
        //TODO 定义一个chess 类封装一下入参
        notifyAllListeners(row,col,color);
        return true;
    }

    public void clear(){
        initChess();

        for(ModelChangeListener listener : listeners){
            listener.clear();
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

        for(int i = row + 1; i < GoBangConstant.ROWS; i++){
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

        for(int i = col + 1; i < GoBangConstant.COLS; i++){
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

        for(int i = row +1,j = col + 1; i< GoBangConstant.ROWS && j < GoBangConstant.COLS;i++,j++){
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
        for(int i = row + 1,j = col -1; i < GoBangConstant.ROWS && j >=0;i++,j--){
            if(state[i][j] != state[row][col]){
                break;
            }
            chessNum += 1;
            if(chessNum >= 5){
                return true;
            }
        }

        for(int i = row -1,j = col + 1; i >= 0  && j < GoBangConstant.COLS;i--,j++){
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

    public PieceColor[][] getState() {
        return state.clone();
    }

    private void notifyAllListeners(int row,int col,PieceColor color){
        for(ModelChangeListener listener : listeners){
            listener.addChess(row,col,color);
        }
    }

    public void addListener(ModelChangeListener listener){
        this.listeners.add(listener);
    }
}
