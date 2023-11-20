package population;

import DiseaseState.IState;
import vectors.Vector2D;

import java.util.Random;


public class Specimen {

    private IState state;
    private Vector2D velocityVector;
    private double x;
    private double y;

    public Specimen(IState initialState, double x, double y){
        this.state = initialState;
        changeVector();
    }

    private void changeVector(){
        Random random = new Random();
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

    public Vector2D getVelocityVector() {
        return velocityVector;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    private boolean isOnBorder(double maxX, double maxY){
        if(x<=0 || x>=maxX){
            return true;
        }
        if(y<=0 || y>=maxX){
            return  true;
        }
        return false;
    }


}
