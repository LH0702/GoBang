package control.record;

public class Memento {

    private final int[][] state ;
    public Memento(int[][] currentState){
        state = currentState;
    }

    public int[][] getState(){
        return state;
    }

}
