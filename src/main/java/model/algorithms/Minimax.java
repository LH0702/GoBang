package model.algorithms;

import model.PieceColor;
import model.role.Role;
import util.GoBangConstant;

public class Minimax {

    public Step go(int depth, int alpha, int beta, Role role, PieceColor[][] node){
        if(depth == 0){
            return new Step(0,0,Score.getInstance().evaluate());
        }

        if(role.isMaximizingPlayer()){
            Step step = new Step(0,0,Integer.MIN_VALUE);
            for(int i = 0; i < GoBangConstant.ROWS; i++){
                for(int j = 0; j < GoBangConstant.COLS; j++){
                    if(node[i][j] != PieceColor.BLANK){
                        continue;
                    }
                    node[i][j] = role.getColor();
                    int score = go(depth - 1,alpha,beta,role.getOpponent(),node).getScore();
                    if(score > step.getScore()){
                        step.setScore(score);
                        step.setX(i);
                        step.setY(j);
                    }

                    node[i][j] = PieceColor.BLANK;
                    alpha = Math.max(score,alpha);
                    if(alpha >= beta){
                        return step;
                    }
                }
            }

            return step;
        }else{
            Step step = new Step(0,0,Integer.MAX_VALUE);
            for(int i = 0; i < GoBangConstant.ROWS ; i++){
                for(int j = 0; j < GoBangConstant.COLS; j++){
                    if(node[i][j] != PieceColor.BLANK){
                        continue;
                    }
                    node[i][j] = role.getColor();
                    int score = go(depth - 1,alpha,beta,role.getOpponent(),node).getScore();
                    if(score < step.getScore()){
                        step.setScore(score);
                        step.setX(i);
                        step.setY(j);
                    }
                    node[i][j] = PieceColor.BLANK;
                    beta = Math.min(score,beta);
                    if(alpha >= beta){
                        return step;
                    }
                }
            }
            return step;
        }
    }
}
