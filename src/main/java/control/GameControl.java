package control;

import java.util.ArrayList;
import java.util.List;

public class GameControl {

    //判断游戏是否开始
    private boolean isStart = false;

    //先手棋子 TODO
    private int firstHand = 0;

    //开始游戏监听
    private List<GoBangObservable> startGameListener = new ArrayList<GoBangObservable>();

    //结束游戏监听
    private List<GoBangObservable> endGameListener = new ArrayList<GoBangObservable>();


    private static final GameControl INSTANCE = new GameControl();

    private GameControl(){

    }

    public static GameControl getInstance(){
        return INSTANCE;
    }

    public boolean isStart(){
        return isStart;
    }

    public void startGame(){
        isStart = true;

        for(GoBangObservable listener : startGameListener){
            listener.update();
        }
    }

    public  void endGame(){
        isStart = false;

        for(GoBangObservable listener :endGameListener){
            listener.update();
        }

    }

    public void addStartListener(GoBangObservable observable){
        startGameListener.add(observable);
    }

    public void addEndListener(GoBangObservable observable){
        endGameListener.add(observable);
    }


    public void clearStartListener(){
        startGameListener.clear();
    }

    public void clearEndListener(){
        endGameListener.clear();
    }

}

