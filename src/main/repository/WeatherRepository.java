package repository;

import model.Coordinates;
import model.WeatherForADay;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reports.CurrentWeatherReport;
import reports.ThreeDayReport;
import reports.WeatherRequest;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepository implements Weather{
    public static final String APIKey = "ede316e0c3443ebcc6e7bbdd85d14595";

    public String buildCurrentWeather(WeatherRequest request){
        URIBuilder builder = new URIBuilder()
            .setScheme("http")
            .setHost("api.openweathermap.org")
            .setPath("data/2.5/weather")
            .addParameter("q", request.getCityName() + "," + request.getCountryCode())
            .addParameter("APPID", APIKey)
            .addParameter("units", "metric");
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e){
            e.printStackTrace();
        }
        return url.toString();
    }

    public JSONObject makeCurrentWeatherRequest(WeatherRequest weatherRequest) {
        String url = buildCurrentWeather(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request){
        JSONObject weatherReportInJson = makeCurrentWeatherRequest(request);
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

    public String buildWeatherForecastURL(WeatherRequest weatherRequest){
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/forecast")
                .addParameter("q", weatherRequest.getCityName() + "," + weatherRequest.getCountryCode())
                .addParameter("units", "metric");
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e){
            e.printStackTrace();
        }
        return url.toString();
    }

    public JSONObject makeForecastRequest(WeatherRequest weatherRequest) {
        String url = buildWeatherForecastURL(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

    @Override
    public ThreeDayReport getForecastForThreeDays(WeatherRequest weatherRequest){
        JSONObject reportInJson = makeForecastRequest(weatherRequest);
        JSONObject cityObject = (JSONObject) reportInJson.get("city");
        JSONObject sys = (JSONObject) reportInJson.get("sys");
        JSONObject coord = (JSONObject) cityObject.get("coord");
        String cityName = (String) cityObject.get("name");
        String countryCode = (String) sys.get("country");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lan");
        Coordinates coordinates = new Coordinates(longitude, latitude);
        double previousMaxTemp = Integer.MIN_VALUE;
        double previousMinTemp = Integer.MAX_VALUE;
        int previousDay = 0;
        JSONArray forecastArray = (JSONArray) reportInJson.get("list");
        List<WeatherForADay> threeDayForecast = new ArrayList<WeatherForADay>();

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
