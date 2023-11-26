
package DiseaseState;
import population.Specimen;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class State {
    public Color color;
    protected int infectionDuration = 0;
    Map<Specimen, Integer> timesMap = new HashMap<>();


    abstract public void infect(Specimen specimen1, Specimen specimen2);

    public Color getColor() {
        return color;
    }
    public boolean isInfected(){
        return infectionDuration > 0;
    }
    public void decreaseInfectionDuration() {
        if (infectionDuration > 0) {
            infectionDuration--;
        }
    }
    public int getInfectionDuration(){
        return infectionDuration;
    }
}
