package model;

import org.json.JSONObject;
import repository.WeatherForOneDay;
import repository.WeatherForThreeDays;

import java.util.ArrayList;
import java.util.List;

public class WeatherRequest {
    public String cityName;
    public String units;
    public String countryCode;

    public WeatherRequest(String cityName, String units, String countryCode) {
        this.cityName = cityName;
        this.units = units;
        this.countryCode = countryCode;

        WeatherForOneDay.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForOneDay.setApiUrl("api.openweathermap.org/data/2.5/weather?q=");
        WeatherForOneDay.changeUnit(units);

        WeatherForThreeDays.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForThreeDays.setApiUrl("api.openweathermap.org/data/2.5/weather?q=");
        WeatherForThreeDays.changeUnit(units);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public WeatherReport getOneDayWeather(){
        JSONObject weatherInfo = WeatherForOneDay.getCityWeatherJSON(cityName);
        double temp = WeatherForOneDay.getCurrentTemperature(weatherInfo);
        WeatherReport report = new WeatherReport(cityName, temp);
        return report;
    }

    public ThreeDayWeatherReport getThreeDayWeather() {
        JSONObject weatherInfo = WeatherForThreeDays.getCityWeatherJSON(cityName);
        ThreeDayWeatherReport report = new ThreeDayWeatherReport(cityName);
        List<ForecastWeatherReport> forecastReports = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            JSONObject forecastObject = WeatherForThreeDays.getForecastForSpecificDay(weatherInfo, i);
            double minTemp = WeatherForThreeDays.getMinTempForThreeDays(forecastObject);
            double maxTemp = WeatherForThreeDays.getMaxTempForThreeDays(forecastObject);

            ForecastWeatherReport forecast = new ForecastWeatherReport(cityName, minTemp, maxTemp);
            forecastReports.add(forecast);
        }

        report.setForecastReports(forecastReports);

        return report;
    }
}
