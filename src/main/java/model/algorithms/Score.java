package model.algorithms;

public class Score {
    private static final Score instance = new Score();

    private Score(){

    }

    public static Score getInstance(){
        return instance;
    }

    public int evaluate(){
        return 0;
    }
}
