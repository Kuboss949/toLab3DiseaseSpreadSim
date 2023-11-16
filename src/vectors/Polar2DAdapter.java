package vectors;

public class Polar2DAdapter implements IVector, IPolar2D{
    private Vector2D srcVector;

    public Polar2DAdapter(Vector2D srcVector) {
        this.srcVector = srcVector;
    }

    @Override
    public double getAngle() {
        double[] components = this.getComponents();
        if(components[0]>0.0 && components[1]>=0.0)
            return Math.atan(components[1]/components[0]);
        else if(components[0]>0.0 && components[1]<0.0)
            return Math.atan(components[1]/components[0]) + 2 * Math.PI;
        else if(components[0] < 0.0)
            return Math.atan(components[1]/components[0]) + Math.PI;
        else if(components[0] == 0.0 && components[1] > 0.0)
            return Math.PI / 2;
        else
            return 3 * Math.PI / 2;
    }

    @Override
    public double abs() {
        return this.srcVector.abs();
    }

    @Override
    public double cdot(IVector vector) {
        return this.srcVector.cdot(vector);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }
}
