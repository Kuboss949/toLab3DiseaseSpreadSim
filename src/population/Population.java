package population;

import DiseaseState.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Population {
    private ArrayList<Specimen> specimenList;
    private double maxX;
    private double maxY;
    private boolean immunity;
    private Random random;



    public Population(int specimenNumber, double n, double m, boolean immunity){
        maxX = n;
        maxY = m;
        random = new Random();
        this.immunity = immunity;
        specimenList = new ArrayList<>(specimenNumber);
        this.generateStartingPopulation(specimenNumber);
    }

    public void movePopulation(){
        for (int i = 0; i < specimenList.size(); i++) {
            Specimen s = specimenList.get(i);
            s.move();

            if (s.isOnBorder(maxX, maxY)) {
                if (random.nextBoolean()) {
                    specimenList.remove(i);
                    i--;
                } else {
                    s.reverseDirection();
                }
            } else {
                s.changeDirection();
            }
            specimenList.stream().forEach(potentialNeighbour -> {s.getState().infect(s, potentialNeighbour);});
            if(i>=0 && specimenList.get(i).getState().isInfected()){
                specimenList.get(i).getState().decreaseInfectionDuration();
                if(!specimenList.get(i).getState().isInfected()){
                    specimenList.get(i).setState(new ImmuneState());
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

    public void drawPopulation(Graphics g, int size, int width, int height){
        Graphics2D g2 = (Graphics2D) g;
        for (var specimen:
             specimenList) {
            g2.setColor(specimen.getState().color);
            int x = (int) (specimen.getX() / maxX * width);
            int y = (int) (specimen.getY() / maxY * height);

            g2.fillOval(x, y, size, size);
        }
    }



}
