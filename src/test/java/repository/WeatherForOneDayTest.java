package repository;

import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class WeatherForOneDayTest {

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

}
