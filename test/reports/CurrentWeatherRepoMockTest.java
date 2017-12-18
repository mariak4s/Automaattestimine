package reports;

import model.Coordinates;
import org.junit.Before;
import org.junit.Test;

import weatherRequest.WeatherRequest;
import repository.Weather;
import repository.WeatherRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrentWeatherRepoMockTest {
    private static CurrentWeatherReport report;
    private static WeatherRequest request;
    private static Coordinates exampleCoordinates;

    private static double temp = 15.4;

    @Before
    public void setUpTests(){
        exampleCoordinates = new Coordinates(24.7, 59.4);
        request = new WeatherRequest("Tallinn", exampleCoordinates, "EE");
        Weather weatherRepo = mock(WeatherRepository.class);
        when(weatherRepo.getCurrentWeather(request)).thenReturn(new CurrentWeatherReport("Tallinn", exampleCoordinates, "EE", temp));
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
            assertNotEquals(null, report.currentTemp);
        } catch (Exception e) {
            fail("Test failed because: " + e.getMessage());
        }
    }


    @Test
    public void testIfResponseHasCorrectTemp(){
        assertEquals(temp, report.currentTemp, 0.01);
    }
}

