
import model.Coordinates;
import model.WeatherForADay;
import org.junit.Before;
import org.junit.Test;
import reports.ThreeDayReport;
import reports.WeatherRequest;
import repository.Weather;
import repository.WeatherRepository;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotEquals;


public class ThreeDayRepoTest {

    private static Coordinates exampleCoordinates;
    private static ThreeDayReport report;
    private static WeatherRequest request;

    @Before
    public void setUpTests(){
        exampleCoordinates = new Coordinates(24.7, 59.4);
        request = new WeatherRequest("Tallinn", exampleCoordinates, "EE");
        Weather weatherRepo = new WeatherRepository();
        try {
            report = weatherRepo.getForecastForThreeDays(request);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCityNameIsCorrect(){
        try {
            assertEquals(report.cityName, request.cityName);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCoordinatesAreCorrect(){
        try {
            assertEquals(report.coordinates.getLatitude(), request.coordinates.getLatitude(), 0.1);
            assertEquals(report.coordinates.getLongitude(), request.coordinates.getLongitude(), 0.1);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCountryCodeIsCorrect(){
        try {
            assertEquals(report.countryCode, request.countryCode);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfHasDailyWeather(){
        try {
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
        assertNotEquals(null, report.dailyWeather);
    }

    @Test
    public void testIfWeatherDayCountIsThree(){
        try {
            int nrOfDaysInRequest = 3;
            assertEquals(nrOfDaysInRequest, report.dailyWeather.length);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfThreeDayForecastHasDailyMaxTemp(){
        try {
            for (WeatherForADay d : report.dailyWeather) {
                assertNotEquals(null, d.getMaxTemp());
            }
        } catch (Exception e) {
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfThreeDayForecastHasDailyMinTemp(){
        try {
            for (WeatherForADay d : report.dailyWeather) {
                assertNotEquals(null, d.getMinTemp());
            }
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfThreeDayForecastMinTempLowerThanMax(){
        try {
            for (WeatherForADay d : report.dailyWeather) {
                double maxTemp = d.getMaxTemp();
                double minTemp = d.getMinTemp();
                System.out.println("max:" + maxTemp + ",min: " + minTemp);
                assertTrue(true);
            }
        } catch (Exception e) {
            fail("Test failed because: " + e.getMessage());
        }
    }


/**
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
    }**/



}
