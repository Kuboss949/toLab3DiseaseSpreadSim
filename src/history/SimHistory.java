package history;

import java.util.ArrayList;
import java.util.List;

public class SimHistory {
    private final List<Memento>  mementoList = new ArrayList<>();

    public void add(Memento memento){
        mementoList.add(memento);
    }

    public Memento get(int index){
        if(index >=0 && index < mementoList.size())
            return mementoList.get(index);
        else
            throw(new IndexOutOfBoundsException());
    }

    public int getHistorySize(){
        return mementoList.size();
    }
}
