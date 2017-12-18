package weatherRequestFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fileReader.FileReader;
import weatherRequest.WeatherRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherRequestFactory {
    public WeatherRequest[] weatherRequests;
    private String inputFile;
    private WeatherRequest weatherRequest;
    private FileReader reader = new FileReader();

    public WeatherRequest[] makeWeatherRequestsFromFile(String inputFile) throws Exception{
       JSONArray input = reader.readFile(inputFile);
       List<WeatherRequest> listOfWeatherRequests = new ArrayList<>();
       for (int i = 0; i < input.size(); ++i){
           JSONObject inputInJSON = (JSONObject)input.get(i);
           String cityName = (String)inputInJSON.get("city");
           String countryCode = (String)inputInJSON.get("countryCode");
           String units = (String)inputInJSON.get("units");
           weatherRequest = new WeatherRequest(cityName, countryCode, units);
           listOfWeatherRequests.add(weatherRequest);
       }
       WeatherRequest[] weatherRequests = listOfWeatherRequests.toArray(new WeatherRequest[listOfWeatherRequests.size()]);
        return weatherRequests;
    }
}
