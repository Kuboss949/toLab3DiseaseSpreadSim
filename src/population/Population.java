package population;

import DiseaseState.IState;
import DiseaseState.ImmuneState;
import DiseaseState.VulnerableState;

import java.util.ArrayList;
import java.util.Random;
import java.util.SortedMap;

public class Population {
    private ArrayList<Specimen> specimenList;
    double maxX;
    double maxY;
    boolean immunity;


    public Population(int specimenNumber, double n, double m, boolean immunity){
        maxX = n;
        maxY = m;
        this.immunity = immunity;
        specimenList = new ArrayList<>();
    }

    public void movePopulation(){
        specimenList.forEach(Specimen::move);
    }

    public void addSpecimen(){
        Random random = new Random();
        double x, y;
        if(random.nextBoolean()){
            x = random.nextDouble(maxX);
            y = random.nextBoolean() ? 0 : maxY - 1;
        }else{
            y = random.nextDouble(maxY);
            x = random.nextBoolean() ? 0 : maxX - 1;
        }
        IState state;
        if(immunity){
            state = random.nextBoolean() ? new ImmuneState() : new VulnerableState();
        }else{
            state = new VulnerableState();
        }
        specimenList.add(new Specimen(state, x, y));
    }





}
