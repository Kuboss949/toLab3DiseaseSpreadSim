
package DiseaseState;
import population.Specimen;

import java.awt.*;

public abstract class State {
    public Color color;
    protected int infectionDuration = 0;
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
}
