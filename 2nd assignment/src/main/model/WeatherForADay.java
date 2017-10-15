package model;

public class WeatherForADay {
    private double maxTemp;
    private double minTemp;

    public WeatherForADay(double maxTemp, double minTemp){
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

}
