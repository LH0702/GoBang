package model.algorithms;

import model.PieceColor;
import model.role.Role;
import util.GoBangConstant;

public class Minimax {

    public int go(int depth, int alpha, int beta, Role role, PieceColor[][] node){
        if(depth == 0){
            return Score.getInstance().evaluate();
        }

        if(role.isMaximizingPlayer()){
            int score = Integer.MIN_VALUE;
            for(int i = 0; i < GoBangConstant.ROWS; i++){
                for(int j = 0; j < GoBangConstant.COLS; j++){
                    if(node[i][j] != PieceColor.BLANK){
                        continue;
                    }
                    node[i][j] = role.getColor();
                    score = Math.max(score,go(depth - 1,alpha,beta,role.getOpponent(),node));
                    node[i][j] = PieceColor.BLANK;
                }
            }

            return score;
        }else{
            int score = Integer.MAX_VALUE;
            for(int i = 0; i < GoBangConstant.ROWS ; i++){
                for(int j = 0; j < GoBangConstant.COLS; j++){
                    if(node[i][j] != PieceColor.BLANK){
                        continue;
                    }
                    node[i][j] = role.getColor();
                    score = Math.min(score,go(depth - 1,alpha,beta,role.getOpponent(),node));
                    node[i][j] = PieceColor.BLANK;
                }
            }
            return score;
        }
    }
}
