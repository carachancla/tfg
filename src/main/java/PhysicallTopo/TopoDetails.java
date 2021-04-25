package PhysicallTopo;

import org.apache.commons.lang3.math.Fraction;

public class TopoDetails {

    private Fraction fraction;
    private double distanceX; //space between servers
    private double distanceY; //space between corridors
    private double distanceZ; //space too cabling rails

    TopoDetails(){
        fraction = Fraction.getFraction(-1);
        distanceX = -1;
        distanceY = -1;
        distanceZ = -1;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }

    public void setDistanceX(Double distanceX) {
        this.distanceX = distanceX;
    }

    public void setDistanceY(Double distanceY) {
        this.distanceY = distanceY;
    }

    public void setDistanceZ(Double distanceZ) {
        this.distanceZ = distanceZ;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }

    public double getDistanceZ() {
        return distanceZ;
    }

}
