/* old version of tests
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;

public class ForecastRepositoryTest {



    @Test
    public void testGetCityByIDShouldReturnCityByID(){
        String cityID = "";
        assertEquals(cityID, ForecastRepository.getCityByID());
    }

    @Test
    public void testGetCoordinatesInGEOShouldReturnCoordinatesInGEO(){
        double latMax = 90;
        double latMin = -90;
        double lngMax = 180;
        double lngMin = -180;
        String response = ForecastRepository.getCoordinatesInGEO();
        assertTrue(response.length() == 7);
        assertTrue(response == (String)response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetCurrentWeatherWithDateAndTimeShouldReturnCurrentWeatherByDateAndTime(){
        SimpleDateFormat response = ForecastRepository.getCurrentWeatherWithDateAndTime();
        assertTrue(response == ForecastRepository.getCurrentWeatherWithDateAndTime());
    }

    @Test
    public void testGetThreeDayForecastShouldReturnThreeDayForecast(){
        String response = ForecastRepository.getThreeDayForecast();
        assertTrue(response == (String)response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetTemperatureShouldReturnInt(){
        int result = ForecastRepository.getTemperature();
        assertTrue(result == (int)result);
    }


    @Test
    public void testGetCurrentTemperatureInCelsiusShouldReturnCurrentTempInCelsius(){
        int response = ForecastRepository.getCurrentTemperatureInCelsius();
        assertTrue(response == (int)response);
        assertTrue(response < 100);
        assertTrue(response > -100);
    }

    @Test
    public void testGetCurrentTemperatureInFahrenheitShouldReturnCurrentTempInFahrenheit(){
        int response = ForecastRepository.getCurrentTemperatureInFahrenheit();
        assertTrue(response == (int)response);
        assertTrue(response < 212);
        assertTrue(response > -148);
    }

    @Test
    public void testIfMaxTemperatureLargerThanMin(){
        assertTrue(ForecastRepository.getMaxTemperature() >= ForecastRepository.getMinTemperature());
        assertFalse(ForecastRepository.getMaxTemperature() < ForecastRepository.getMinTemperature());
    }

}
*/
