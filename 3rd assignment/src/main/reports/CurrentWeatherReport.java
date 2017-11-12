package reports;

import model.Coordinates;

public class CurrentWeatherReport extends WeatherReport {
    public final double currentTemp;

    public CurrentWeatherReport(String cityName, Coordinates coordinates, String countryCode, double currentTemp){
        super(cityName, coordinates, countryCode);
        this.currentTemp = currentTemp;
    }
}
