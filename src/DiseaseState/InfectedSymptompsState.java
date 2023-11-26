package DiseaseState;

import population.Specimen;

import java.awt.*;

public class InfectedSymptompsState extends State {

    public InfectedSymptompsState(int duration){
        color = Color.red;
        infectionDuration = duration;
    }
    @Override
    public void infect(Specimen specimen1, Specimen specimen2) {

    }
}
