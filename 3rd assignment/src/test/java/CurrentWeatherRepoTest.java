
import model.Coordinates;
import org.junit.Before;
import org.junit.Test;
import reports.CurrentWeatherReport;
import reports.WeatherRequest;
import repository.Weather;
import repository.WeatherRepository;

import static org.junit.Assert.*;

public class CurrentWeatherRepoTest {
    private static CurrentWeatherReport report;
    private static WeatherRequest request;
    private static Coordinates exampleCoordinates;

    @Before
    public void setUpTests(){
        exampleCoordinates = new Coordinates(24.7, 59.4);
        request = new WeatherRequest("Tallinn", exampleCoordinates, "EE");
        Weather weatherRepo = new WeatherRepository();
        try {
            report = weatherRepo.getCurrentWeather(request);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReturnsCorrectCity(){
        try {
            // Weather weatherRepo = new WeatherRepository();
            // CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            assertEquals(request.cityName, report.cityName);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReturnsCorrectCoordinates(){
        try {
            // Weather weatherRepo = new WeatherRepository();
            // CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            assertEquals(request.coordinates.getLongitude(), report.coordinates.getLongitude(), 0.1);
            assertEquals(request.coordinates.getLatitude(), report.coordinates.getLatitude(), 0.1);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReturnsCorrectCountryCode(){
        try {
            // Weather weatherRepo = new WeatherRepository();
            // CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            assertEquals(request.countryCode, report.countryCode);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfResponseHasTemp(){
        try {
            // Weather weatherRepo = new WeatherRepository();
            // CurrentWeatherReport report = weatherRepo.getCurrentWeather(request);
            assertNotEquals(null, report.currentTemp);
        } catch (Exception e) {
            fail("Test failed because: " + e.getMessage());
        }
    }
/**
    @Test
    public void testIfReturnsCityWeatherJson(){
        WeatherForOneDay.setApiUrl("api.openweathermap.org/data/2.5/weatherforecast?q=");
        WeatherForOneDay.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForOneDay.changeUnit("Metric");
        try {
            JSONObject weatherInfo = WeatherForOneDay.getCityWeatherJSON("Tallinn");
            assertTrue(weatherInfo == (JSONObject)weatherInfo);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCityNameIsCorrect(){
        WeatherForOneDay.setApiUrl("api.openweathermap.org/data/2.5/weatherforecast?q=");
        WeatherForOneDay.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForOneDay.changeUnit("Metric");
        JSONObject weatherInfo;
        String requestCity = "Tallinn";

        try {
            weatherInfo = WeatherForOneDay.getCityWeatherJSON("Tallinn");
            String result = WeatherForOneDay.getCityByName(weatherInfo);
            assertEquals(requestCity, result);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfTempInDouble(){
        WeatherForOneDay.setApiUrl("api.openweathermap.org/data/2.5/weatherforecast?q=");
        WeatherForOneDay.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForOneDay.changeUnit("Metric");
        JSONObject weatherInfo;

        try {
            weatherInfo = WeatherForOneDay.getCityWeatherJSON("Tallinn");
            double result = WeatherForOneDay.getCurrentTemperature(weatherInfo);
            return result;
        } catch (NumberFormatException ex) {
            return false;
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }

    }
    **/

}
