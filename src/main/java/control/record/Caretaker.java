package control.record;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    private static final Caretaker INSTANCE = new Caretaker();

    private Caretaker(){

    }

    public static Caretaker getInstance(){
        return  INSTANCE;
    }

    public void addMemento(Memento memento){
        mementoList.add(memento);
    }

}
