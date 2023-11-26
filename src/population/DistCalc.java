package population;

public class DistCalc {
    public static double calculateDistance(Specimen s1, Specimen s2){
        return Math.sqrt((s1.getX()-s2.getX())*(s1.getX()-s2.getX()) + (s1.getY()-s2.getY()) * (s1.getY()-s2.getY()));
    }
}
