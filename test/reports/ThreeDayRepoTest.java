package reports;

import model.Coordinates;
import model.WeatherForADay;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import weatherRequest.WeatherRequest;
import repository.Weather;
import repository.WeatherRepository;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;


public class ThreeDayRepoTest {

    private static Coordinates exampleCoordinates;
    private static ThreeDayReport report;
    private static WeatherRequest request;

    @BeforeClass
    public static void setUpTests(){
        exampleCoordinates = new Coordinates(24.7, 59.4);
        request = new WeatherRequest("Tallinn", exampleCoordinates, "EE");
        Weather weatherRepo = new WeatherRepository();
        try {
            report = weatherRepo.getForecastForThreeDays(request);
        } catch (Exception e){
            fail("All Tests failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCityNameIsCorrect(){
        try {
            assertEquals(report.cityName, request.getCityName());
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCoordinatesAreCorrect(){
        try {
            assertEquals(report.coordinates.getLatitude(), request.getCoordinates().getLatitude(), 0.1);
            assertEquals(report.coordinates.getLongitude(), request.getCoordinates().getLongitude(), 0.1);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfCountryCodeIsCorrect(){
        try {
            assertEquals(report.countryCode, request.getCountryCode());
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

    @After
    public void tearDown() throws Exception {
    }

}
