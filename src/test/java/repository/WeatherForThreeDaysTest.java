package repository;

import model.WeatherReport;
import model.WeatherRequest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;

public class WeatherForThreeDaysTest {

    @Test
    public void testIfReturnsCityWeatherJson(){
        WeatherForThreeDays.setApiUrl("api.openweathermap.org/data/2.5/weatherforecast?q=");
        WeatherForThreeDays.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForThreeDays.setUnits("Metric");
        try {
            JSONObject weatherInfo = WeatherForThreeDays.getCityWeatherJSON("Tallinn");
            assertTrue(weatherInfo == (JSONObject)weatherInfo);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCityNameIsCorrect(){
        WeatherForThreeDays.setApiUrl("api.openweathermap.org/data/2.5/weatherforecast?q=");
        WeatherForThreeDays.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForThreeDays.setUnits("Metric");
        JSONObject weatherInfo;
        String requestCity = "Tallinn";

        try {
            weatherInfo = WeatherForThreeDays.getCityWeatherJSON("Tallinn");
            String result = WeatherForThreeDays.getCityByName(weatherInfo);
            assertEquals(requestCity, result);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfMaxTemperatureInDouble(){
        WeatherForThreeDays.setApiUrl("api.openweathermap.org/data/2.5/weatherforecast?q=");
        WeatherForThreeDays.setApiKey("ede316e0c3443ebcc6e7bbdd85d14595");
        WeatherForThreeDays.setUnits("Metric");
        try {
            JSONObject weatherInfo = WeatherForThreeDays.getCityWeatherJSON();

        }
        double result = WeatherForThreeDays.getMaxTempForThreeDays();
        Assert.assertTrue(result == (int)result);
    }

    @Test
    public void testIfMinTemperatureInDouble(){
        double result = WeatherForThreeDays.getMinTempForThreeDays();
        Assert.assertTrue(result == (int)result);
    }

    @Test
    public void testIfMaxBiggerThanMinTemp(){
        double minTemp = WeatherForThreeDays.getMinTempForThreeDays();
        double maxTemp = WeatherForThreeDays.getMaxTempForThreeDays();
        Assert.assertTrue(maxTemp > minTemp);
    }

    /**@Test
    public void testIfUnitsAreCorrect(){
        String units = WeatherForThreeDays.getUnits();
        String testUnits = "kilograms";
        try {
            WeatherRequest.(){

            }
        }
    }*//



}
