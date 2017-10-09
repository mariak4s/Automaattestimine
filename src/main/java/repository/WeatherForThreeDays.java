package repository;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherForThreeDays implements Weather{
    private static String apiUrl;
    private static String apiKey;
    private static String units;

    public static void setApiKey(String apiKey){
        WeatherForThreeDays.apiKey = apiKey;
    }

    public static String getApiKey(){
        return apiKey;
    }

    public static void setApiUrl(String apiUrl){
        WeatherForThreeDays.apiUrl = apiUrl;
    }

    public static String getApiUrl(){
        return apiUrl;
    }

    public static JSONObject getCityWeatherJSON(String city){
        JSONObject weatherInfo = null;
        try {
            /** sample for metric api.openweathermap.org/data/2.5/find?q=London&units=metric **/
            URL fcURL = new URL(apiUrl + city +  "&units=" + units + "&appid=" + apiKey);
            URLConnection connection = fcURL.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String rdResult = rd.readLine();
            rd.close();
            try {
                weatherInfo = new JSONObject(rdResult);
                return weatherInfo;
            } catch (JSONException e){
                System.out.println(e.getMessage());
            }
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return weatherInfo;
    }

    public static JSONObject getForecastForSpecificDay(JSONObject weatherInfo, int numberOfDay){
        JSONArray jsonArray;
        JSONObject dayForecast = null;
        // dt - time of data calculation
        try {
            jsonArray = weatherInfo.getJSONArray("jsonArray");
            dayForecast = jsonArray.getJSONObject(numberOfDay);
        } catch (JSONException e){
            System.out.println(e.getMessage());
        } return dayForecast;
    }

    public static String getCityByName(JSONObject weatherInfo){
        String cityName = "";
        try {
            cityName = weatherInfo.getString("name");
        } catch (JSONException e){
            System.out.println(e.getMessage());
        } return cityName;
    }

    public static double getMinTempForThreeDays(JSONObject weatherInfo){
        try {
            JSONObject main = weatherInfo.getJSONObject("main");
            double minTemp = main.getDouble("temp_min");
            return minTemp;
        } catch (JSONException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static double getMaxTempForThreeDays(JSONObject weatherInfo){
        try {
            JSONObject main = weatherInfo.getJSONObject("main");
            double maxTemp = main.getDouble("temp_min");
            return maxTemp;
        } catch (JSONException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static String changeUnit(String newUnit){
        // Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
        if (newUnit == "Metric" || newUnit == "Imperial" || newUnit == "Kelvin"){
            units = newUnit;
            return "Units changed to: " + newUnit;
        } else {
            return "Units not changed.";
        }
    }

    public static void setUnits(String units){
        WeatherForThreeDays.units = units;
    }
    public static String getUnits(){return units;}

}