package population;

import DiseaseState.IState;
import DiseaseState.ImmuneState;
import DiseaseState.VulnerableState;

import java.util.ArrayList;
import java.util.Random;
import java.util.SortedMap;

public class Population {
    private ArrayList<Specimen> specimenList;


    public Population(int specimenNumber, double n, double m, boolean immunity){
        Random random = new Random();
        specimenList = new ArrayList<>();
        /*IState state;
        for(int i = 0; i < specimenNumber; i++){
            if(immunity){
                state = random.nextBoolean() ? new ImmuneState() : new VulnerableState();
            }else{
                state = new VulnerableState();
            }
            specimenList.add(new Specimen(state, random.nextDouble()*n, random.nextDouble()*m));
        }*/
    }

    public void movePopulation(){
        specimenList.forEach(Specimen::move);
    }





}
