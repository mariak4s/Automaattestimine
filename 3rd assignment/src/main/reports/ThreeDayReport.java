package reports;

import model.Coordinates;
import model.WeatherForADay;

public class ThreeDayReport extends WeatherReport{
    public final WeatherForADay[] dailyWeather;

    public ThreeDayReport(String cityName, Coordinates coordinates, String countryCode, WeatherForADay[] dailyWeather){
        super(cityName, coordinates, countryCode);
        this.dailyWeather = dailyWeather;
    }

    public WeatherForADay[] getDailyWeather() {
        return dailyWeather;
    }

}
