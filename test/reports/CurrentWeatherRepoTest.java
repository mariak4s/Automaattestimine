package reports;

import model.Coordinates;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import repository.Weather;
import repository.WeatherRepository;
import weatherRequest.WeatherRequest;

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
            assertEquals(request.getCityName(), report.cityName);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReturnsCorrectCoordinates(){
        try {
            assertEquals(request.getCoordinates().getLongitude(), report.coordinates.getLongitude(), 0.1);
            assertEquals(request.getCoordinates().getLatitude(), report.coordinates.getLatitude(), 0.1);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReturnsCorrectCountryCode(){
        try {
            assertEquals(request.getCountryCode(), report.countryCode);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfResponseHasTemp(){
        try {
            double currentTemp = report.getCurrentTemp();
            System.out.println("currentTemp: " + currentTemp);
            assertNotEquals(null, report.currentTemp);
        } catch (Exception e) {
            fail("Test failed because: " + e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }
}
