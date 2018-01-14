package control;

public class GameControl {

    //判断游戏是否开始
    private boolean isStart = false;

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
    }

    public  void endGame(){
        isStart = false;
    }
}
