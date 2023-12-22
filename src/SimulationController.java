import history.Memento;
import population.Population;
import population.Specimen;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class SimulationController {


    final int specimenSize = 12;
    Population population;
    private final double maxX;
    private final double maxY;
    private int simTime;
    public SimulationController(double m, double n){
        population = new Population(200, n, m,  false);
        maxX = n;
        maxY = m;
        simTime = 0;
    }

    public void performSimulationStep(){
        population.movePopulation();
        if(Math.random()>0.3){
            population.addSpecimen();
        }
        simTime++;
    }

    public void drawPopulation(Graphics g, int width, int height){
        try{
        Graphics2D g2 = (Graphics2D) g;
        ArrayList<Specimen> specimenList = population.getSpecimenList();
            for (var specimen: specimenList) {
                g2.setColor(specimen.getState().color);
                int x = (int) (specimen.getX() / maxX * width);
                int y = (int) (specimen.getY() / maxY * height);

                g2.fillOval(x, y, specimenSize, specimenSize);
            }
        }catch(ConcurrentModificationException e){

            }
    }

    public Memento saveMemento(){
        return new Memento(population, simTime);
    }
    public void loadMemento(Memento memento){
        try {
            this.population = (Population) memento.getStatePopulation().clone();
            this.simTime = memento.getStateSimTime();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public int getSimTime() {
        return simTime;
    }
}
