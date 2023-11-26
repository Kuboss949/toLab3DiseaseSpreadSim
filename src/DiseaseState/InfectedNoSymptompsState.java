package DiseaseState;

import population.Specimen;

import java.awt.*;

public class InfectedNoSymptompsState extends State {

    public InfectedNoSymptompsState(int duration){
        color = Color.magenta;
        infectionDuration = duration;
    }

    @Override
    public void infect(Specimen specimen1, Specimen specimen2) {

    }
}
