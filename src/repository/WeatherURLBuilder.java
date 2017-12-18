package repository;

import org.apache.http.client.utils.URIBuilder;
import weatherRequest.WeatherRequest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class WeatherURLBuilder {
    public static final String APIKey = "ede316e0c3443ebcc6e7bbdd85d14595";

    public static String buildCurrentWeather(WeatherRequest request){
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

    public static String buildWeatherForecastURL(WeatherRequest weatherRequest){
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/forecast")
                .addParameter("q", weatherRequest.getCityName() + "," + weatherRequest.getCountryCode())
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
}
