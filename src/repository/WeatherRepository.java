package repository;

import model.Coordinates;
import reports.CurrentWeatherReport;
import model.WeatherForADay;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reports.ThreeDayReport;
import weatherRequest.WeatherRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepository implements Weather{

    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest weatherRequest){
        JSONObject weatherReportInJson = weatherRequest.makeCurrentWeatherRequest(weatherRequest);
        JSONObject sys = (JSONObject) weatherReportInJson.get("sys");
        JSONObject main = (JSONObject) weatherReportInJson.get("main");
        JSONObject coord = (JSONObject) weatherReportInJson.get("coord");
        String cityName = (String) weatherReportInJson.get("name");
        String countryCode = (String) sys.get("country");
        long tempCurrent = (long) main.get("temp");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        Coordinates coordinates = new Coordinates(longitude, latitude);
        return new CurrentWeatherReport(cityName, coordinates, countryCode, tempCurrent);
    }


    @Override
    public ThreeDayReport getForecastForThreeDays(WeatherRequest weatherRequest){
        JSONObject reportInJson = weatherRequest.makeForecastRequest(weatherRequest);
        JSONObject cityObject = (JSONObject) reportInJson.get("city");
        //JSONObject sys = (JSONObject) reportInJson.get("sys");
        JSONObject coord = (JSONObject) cityObject.get("coord");
        String cityName = (String) cityObject.get("name");
        String countryCode = (String) cityObject.get("country");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        Coordinates coordinates = new Coordinates(longitude, latitude);
        double previousMaxTemp = Integer.MIN_VALUE;
        double previousMinTemp = Integer.MAX_VALUE;
        int previousDay = 0;
        JSONArray forecastArray = (JSONArray) reportInJson.get("list");
        List<WeatherForADay> threeDayForecast = new ArrayList<>();

        for (int i = 0; i < forecastArray.size(); i++){
            JSONObject oneForecast = (JSONObject) forecastArray.get(i);
            JSONObject main = (JSONObject) oneForecast.get("main");
            Timestamp timestamp = new Timestamp((Long) oneForecast.get("dt") * 1000);

            Object minTempObj = main.get("temp_min");
            Object maxTempObj = main.get("temp_max");
            double minTemp = new Double(minTempObj.toString());
            double maxTemp = new Double(maxTempObj.toString());
            int currentDayOfMonth = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
            int numberOfDaysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - currentDayOfMonth;
            if (numberOfDaysFromToday < 4){
                if (minTemp < previousMinTemp){
                    previousMinTemp = minTemp;
                }
                if (maxTemp > previousMaxTemp) {
                    previousMaxTemp = maxTemp;
                }
                if (numberOfDaysFromToday > previousDay) {
                    WeatherForADay oneDay = new WeatherForADay(previousMaxTemp, previousMinTemp);
                    threeDayForecast.add(oneDay);
                    previousMaxTemp = Integer.MIN_VALUE;
                    previousMinTemp = Integer.MAX_VALUE;
                }
            }
            previousDay = numberOfDaysFromToday;
        }
        WeatherForADay[] threeDays = threeDayForecast.toArray(new WeatherForADay[threeDayForecast.size()]);

        return new ThreeDayReport(cityName, coordinates, countryCode, threeDays);
    }
}
