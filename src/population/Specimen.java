package population;

import DiseaseState.*;
import vectors.Vector2D;

import java.util.Random;


public class Specimen {

    private State state;
    private Vector2D velocityVector;
    private double x;
    private double y;
    private final Random random;

    public Specimen(State initialState, double x, double y){
        this.state = initialState;
        this.x = x;
        this.y = y;
        random = new Random();
        changeVector();
    }
    public Specimen(Specimen original){
        this.y = original.y;
        this.x = original.x;
        this.velocityVector = new Vector2D(original.velocityVector);
        this.random = original.random;
        if(original.state instanceof VulnerableState){
            this.state = new VulnerableState(original.state);
        }else if (original.state instanceof ImmuneState){
            this.state = new ImmuneState();
        }else if (original.state instanceof InfectedSymptompsState){
            this.state = new InfectedSymptompsState(original.state.getInfectionDuration());
        }else {
            this.state = new InfectedNoSymptompsState(original.state.getInfectionDuration());
        }

    }

    private void changeVector(){
        int sign = random.nextDouble() > 0.5 ? 1 : -1;
        double x = sign * random.nextDouble()*2.5;
        sign = random.nextDouble() > 0.5 ? 1 : -1;
        double maxY = sign * Math.sqrt(2.5 * 2.5 - x * x);
        double y = -maxY + random.nextDouble() * (2 * maxY);
        this.velocityVector = new Vector2D(x, y);
    }

    public void move(){
        this.x += 0.04 * this.velocityVector.getComponents()[0];
        this.y += 0.04 * this.velocityVector.getComponents()[1];
    }
    public void changeDirection(){
        if(random.nextDouble()>0.8){
            changeVector();
        }
    }
    public void reverseDirection(){
        this.velocityVector = new Vector2D( -this.velocityVector.getComponents()[0], -this.velocityVector.getComponents()[1]);
    }

    public Vector2D getVelocityVector() {
        return velocityVector;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isOnBorder(double maxX, double maxY){
        if(x<=0 || x>=maxX){
            return true;
        }
        if(y<=0 || y>=maxX){
            return  true;
        }
        return false;
    }


    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

}
