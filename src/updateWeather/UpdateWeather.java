package updateWeather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Coordinates;
import model.WeatherForADay;
import reports.CurrentWeatherReport;
import reports.ThreeDayReport;
import fileWriter.FileWriter;

import repository.WeatherRepository;
import weatherRequest.WeatherRequest;
import weatherRequestFactory.WeatherRequestFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UpdateWeather {
    public static final String INPUT_FILE = new File(System.getProperty("user.dir"), "input.txt").getPath();
    public static final String OUTPUT_PATH = new File(System.getProperty("user.dir"), "outputs").getPath();

    public List<JSONObject> updateWeather(WeatherRepository repository, WeatherRequest[] requests) throws Exception{
        List<JSONObject> jsonObjects = new ArrayList<>();
        //WeatherRepository weatherRepository = new WeatherRepository();
        // FileWriter fileWriter = new FileWriter();
        for (WeatherRequest request : requests) {
            ThreeDayReport threeDayReport = repository.getForecastForThreeDays(request);
            CurrentWeatherReport currentWeatherReport = repository.getCurrentWeather(request);
            JSONObject fullReportJson = makeReportsToJson(threeDayReport, currentWeatherReport);
            jsonObjects.add(fullReportJson);
            //String filename = threeDayReport.getCityName();
            // String OUTPUT_FILE = OUTPUT_PATH + filename + ".txt";
            // FileWriter.writeJsonIntoFile(fullReportJson, OUTPUT_FILE);
        }
        return jsonObjects;
    }

    public JSONObject makeReportsToJson(ThreeDayReport threeDayReport, CurrentWeatherReport currentWeatherReport) {
        JSONObject fullWeatherReport = new JSONObject();
        String city = threeDayReport.getCityName();
        Coordinates coordinates = threeDayReport.getCoordinates();
        JSONObject coordJSON = new JSONObject();
        double lat = coordinates.getLatitude();
        double lon = coordinates.getLongitude();
        coordJSON.put("longitude", lon);
        coordJSON.put("latitude", lat);
        WeatherForADay[] dailyWeathers = threeDayReport.getDailyWeather();
        JSONArray dayWeathersJSON = new JSONArray();
        for (WeatherForADay dailyWeather : dailyWeathers) {
            JSONObject dailyWeatherJSON = new JSONObject();
            double maxTemp = dailyWeather.getMaxTemp();
            double minTemp = dailyWeather.getMinTemp();
            dailyWeatherJSON.put("maxTemp", maxTemp);
            dailyWeatherJSON.put("minTemp", minTemp);
            dayWeathersJSON.add(dailyWeatherJSON);
        }
        double currentTemp = currentWeatherReport.getCurrentTemp();
        fullWeatherReport.put("city", city);
        fullWeatherReport.put("coordinates", coordJSON);
        fullWeatherReport.put("dailyWeather", dayWeathersJSON);
        fullWeatherReport.put("CurrentTemp", currentTemp);

        return fullWeatherReport;
    }

    public void writeReportsToFile(List<JSONObject> jsonObjects) {
        for (JSONObject fullReportJson : jsonObjects) {
            String filename = (String) fullReportJson.get("city");
            String outputFile = new File(OUTPUT_PATH, filename + ".txt").getPath();
            FileWriter.writeJsonIntoFile(fullReportJson, outputFile);
        }
    }

}
