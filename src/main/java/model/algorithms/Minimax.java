package model.algorithms;

import model.PieceColor;
import model.Role;

public class Minimax {

    private static final  int width = 15;
    private static final int height = 15;
    public int go(int depth, Role role, PieceColor[][] node){
        if(depth == 0){
            return Score.getInstance().evaluate();
        }

        if(role.isMaximizingPlayer()){
            int score = Integer.MIN_VALUE;
            for(int i = 0; i < width ; i++){
                for(int j = 0; j < height; j++){
                    if(node[i][j] != PieceColor.BLANK){
                        continue;
                    }
                    node[i][j] = role.getColor();
                    score = Math.max(score,go(depth - 1, role.getOpponent(),node));
                    node[i][j] = PieceColor.BLANK;
                }
            }

            return score;
        }else{
            int score = Integer.MAX_VALUE;
            for(int i = 0; i < width ; i++){
                for(int j = 0; j < height; j++){
                    if(node[i][j] != PieceColor.BLANK){
                        continue;
                    }
                    node[i][j] = role.getColor();
                    score = Math.min(score,go(depth - 1, role.getOpponent(),node));
                    node[i][j] = PieceColor.BLANK;
                }
            }
            return score;
        }
    }
}
