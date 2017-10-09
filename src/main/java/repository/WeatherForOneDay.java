package repository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherForOneDay implements Weather{
    private static String apiUrl;
    private static String apiKey;
    private static String units;

    public static void setApiKey(String apiKey){
        WeatherForOneDay.apiKey = apiKey;
    }

    public static String getApiKey(){
        return apiKey;
    }

    public static void setApiUrl(String apiUrl){
        WeatherForOneDay.apiUrl = apiUrl;
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

    public static String getCityByName(JSONObject weatherInfo){
        String cityName = "";
        try {
            cityName = weatherInfo.getString("name");
        } catch (JSONException e){
            System.out.println(e.getMessage());
        } return cityName;
    }

    public static double getCurrentTemperature(JSONObject weatherInfo){
        double currentTemp = 0;
        try {
            JSONObject main = weatherInfo.getJSONObject("main");
            currentTemp = main.getDouble("temp");
            return currentTemp;
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

}
