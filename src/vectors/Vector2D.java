package vectors;

public class Vector2D implements IVector {

    protected double x;
    protected double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public double abs() {
        double[] components = this.getComponents();
        double sum = 0;
        for (double component : components) {
            sum += component * component;
        }
        return Math.sqrt(sum);
    }

    @Override
    public double cdot(IVector vector) {
        double[] components1 = this.getComponents();
        double[] components2 = vector.getComponents();
        double sum = 0;
        double lower = Math.min(components1.length, components2.length);
        for (int i = 0; i < components1.length;i++){
            sum += components1[i]*components2[i];
        }
        return sum;
    }

    @Override
    public double[] getComponents() {
        return new double[]{x,y};
    }
}
