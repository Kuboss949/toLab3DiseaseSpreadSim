package history;

import population.Population;

public class Memento {
    private Population population;
    private int simTime;
    public Memento(Population population, int simTime){
        try {
            this.population = (Population) population.clone();
            this.simTime = simTime;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public Population getStatePopulation(){
        return population;
    }
    public int getStateSimTime(){
        return simTime;
    }
}
