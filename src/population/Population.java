package population;

import DiseaseState.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Population implements Cloneable{
    private ArrayList<Specimen> specimenList;

    private boolean immunity;
    private Random random;
    private double maxX;
    private double maxY;



    public Population(int specimenNumber, double n, double m, boolean immunity){
        maxX = n;
        maxY = m;
        random = new Random();
        this.immunity = immunity;
        specimenList = new ArrayList<>(specimenNumber);
        this.generateStartingPopulation(specimenNumber);
    }

    public void movePopulation(){
        Iterator<Specimen> iterator = specimenList.iterator();
        while (iterator.hasNext()) {
            Specimen s = iterator.next();
            s.move();

            if (s.isOnBorder(maxX, maxY)) {
                if (random.nextBoolean()) {
                    iterator.remove(); // Use iterator to remove the element safely
                } else {
                    s.reverseDirection();
                }
            } else {
                s.changeDirection();
            }
            specimenList.stream().forEach(potentialNeighbour -> {s.getState().infect(s, potentialNeighbour);});
            if(s.getState().isInfected()){
                s.getState().decreaseInfectionDuration();
                if(!s.getState().isInfected()){
                    s.setState(new ImmuneState());
                }
            }
        }
    }
    private void generateStartingPopulation(int number){
        for (int i = 0; i<number; i++){
            State state;
            state = RandomStateSetter.notInfectedRandom(immunity);
            specimenList.add(new Specimen(state, random.nextDouble()*maxX, random.nextDouble()*maxY));
        }
    }

    public void addSpecimen(){

        double x, y;
        if(random.nextBoolean()){
            x = random.nextDouble(maxX);
            y = random.nextBoolean() ? 0 : maxY;
        }else{
            y = random.nextDouble(maxY);
            x = random.nextBoolean() ? 0 : maxX;
        }
        State state;
        if(random.nextDouble()>0.1){
            state = RandomStateSetter.notInfectedRandom(immunity);
        }else{
            state = RandomStateSetter.infectedRandom();
        }

        specimenList.add(new Specimen(state, x, y));
    }

    public ArrayList<Specimen> getSpecimenList() {
        return specimenList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Population cloned = (Population) super.clone();
        // Create a new list and add cloned elements
        ArrayList<Specimen> copyList = new ArrayList<>();
        for (Specimen originalSpecimen : this.specimenList) {
            // Use the copy constructor of Specimen for deep copy
            copyList.add(new Specimen(originalSpecimen));
        }
        cloned.specimenList = copyList;
        return cloned;
    }
}
