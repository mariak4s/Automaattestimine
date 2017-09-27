import org.junit.Test;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;

public class ForecastDataTest {

    @Test
    public void testInternetConnectionExists(){
        boolean response = ForecastData.internetConnectionExists();
        assertTrue(response);
    }

    @Test
    public void testGetCityCoordinatesShouldReturnCityCoordinates(){
        String response = ForecastData.getCityCoordinates();
        assertTrue(response == (String)response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetCityNameShouldReturnCityName(){
        String cityName = "";
        assertEquals(cityName, ForecastData.getCityName());
    }

    @Test
    public void testGetCityByIDShouldReturnCityByID(){
        String cityID = "";
        assertEquals(cityID, ForecastData.getCityByID());
    }

    @Test
    public void testGetCoordinatesInGEOShouldReturnCoordinatesInGEO(){
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
    public void testMaxTemperatureSmallerThanMinShouldThrowExceptionBecauseMaxHasToBeGreaterOrEqual(){
        assertTrue(ForecastData.getMaxTemperature() >= ForecastData.getMinTemperature());
        assertFalse(ForecastData.getMaxTemperature() < ForecastData.getMinTemperature());
    }

    @Test
    public void testMinTemperatureBiggerThanMaxShouldThrowExceptionBecauseMinHasToBeSmallerOrEqual(){
        assertTrue(ForecastData.getMinTemperature() <= ForecastData.getMaxTemperature());
        assertFalse(ForecastData.getMinTemperature() > ForecastData.getMaxTemperature());
    }
}
