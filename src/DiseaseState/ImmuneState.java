package DiseaseState;

import population.Specimen;

import java.awt.*;

public class ImmuneState extends State {

    public ImmuneState(){
        color = Color.green;
    }

    @Override
    public void infect(Specimen specimen1, Specimen specimen2) {

    }
}
