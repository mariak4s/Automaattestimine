import org.junit.Test;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;

public class ForecastDataTest {

    @Test
    public void testGetCityCoordinatesShouldReturnCityCoordinates(){
        String cityName = "Tallinn";
        String expectedCoordinates = "59.437222;24.745278";
        String coordinates = ForecastData.getCityCoordinates(cityName);
        assertEquals(expectedCoordinates, coordinates);
    }

    @Test
    public void testGetCityByIDShouldReturnCityByID(){
        String cityID = "";
        assertEquals(cityID, ForecastData.getCityByID());
    }

    @Test
    public void testGetCoordinatesInGEOShouldReturnCoordinatesInGEO(){
        /*double latMax = 90;
        double latMin = -90;
        double lngMax = 180;
        double lngMin = -180;*/
        String response = ForecastData.getCoordinatesInGEO();
        assertTrue(response.length() == 7);
        assertTrue(response == (String)response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetCurrentWeatherWithDateAndTimeShouldReturnCurrentWeatherByDateAndTime(){
        SimpleDateFormat response = ForecastData.getCurrentWeatherWithDateAndTime();
        assertTrue(response == ForecastData.getCurrentWeatherWithDateAndTime());
    }

    @Test
    public void testGetThreeDayForecastShouldReturnThreeDayForecast(){
        String response = ForecastData.getThreeDayForecast();
        assertTrue(response == (String)response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetTemperatureShouldReturnInt(){
        int result = ForecastData.getTemperature();
        assertTrue(result == (int)result);
    }


    @Test
    public void testGetCurrentTemperatureInCelsiusShouldReturnCurrentTempInCelsius(){
        int response = ForecastData.getCurrentTemperatureInCelsius();
        assertTrue(response == (int)response);
        assertTrue(response < 100);
        assertTrue(response > -100);
    }

    @Test
    public void testGetCurrentTemperatureInFahrenheitShouldReturnCurrentTempInFahrenheit(){
        int response = ForecastData.getCurrentTemperatureInFahrenheit();
        assertTrue(response == (int)response);
        assertTrue(response < 212);
        assertTrue(response > -148);
    }

    @Test
    public void testIfMaxTemperatureLargerThanMin(){
        assertTrue(ForecastData.getMaxTemperature() >= ForecastData.getMinTemperature());
        assertFalse(ForecastData.getMaxTemperature() < ForecastData.getMinTemperature());
    }

}
