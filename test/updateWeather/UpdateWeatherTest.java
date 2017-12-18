package updateWeather;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import model.Coordinates;
import model.WeatherForADay;
import reports.CurrentWeatherReport;
import reports.ThreeDayReport;
import weatherRequest.WeatherRequest;
import repository.WeatherRepository;
import weatherRequestFactory.WeatherRequestFactory;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import static junit.framework.TestCase.fail;

public class UpdateWeatherTest {
    private static WeatherRequestFactory factory;
    private static WeatherRepository repoMock;
    private static UpdateWeather weatherUpdate;

    @BeforeClass
    public static void setUpTests() throws Exception {
        repoMock = mock(WeatherRepository.class);
        factory = mock(WeatherRequestFactory.class);
        weatherUpdate = new UpdateWeather();

        Coordinates mockCoordinates = new Coordinates(24.7, 59.4);
        WeatherForADay firstDayWeather = new WeatherForADay(10.1, -13.2);
        WeatherForADay secondDayWeather = new WeatherForADay(3.0, -2.3);
        WeatherForADay thirdDayWeather = new WeatherForADay(3.2, 1.0);
        WeatherForADay[] randomDailyWeathers = {firstDayWeather, secondDayWeather, thirdDayWeather};
        CurrentWeatherReport mockCurrentWeatherReport = new CurrentWeatherReport("Tallinn", mockCoordinates, "EE", 13);
        ThreeDayReport mockThreeDayReport = new ThreeDayReport("Tallinn", mockCoordinates, "EE", randomDailyWeathers);

        when(repoMock.getCurrentWeather(any(WeatherRequest.class))).thenReturn(mockCurrentWeatherReport);
        when(repoMock.getForecastForThreeDays(any(WeatherRequest.class))).thenReturn(mockThreeDayReport);
    }

    private static void emptyFolder(File folder) throws IOException{
        File[] files = folder.listFiles();
        if (files != null){
            for (File file : files){
                if (!file.delete()){
                    throw new IOException("Could not delete file!");
                }
            }
        } else {
            throw new IOException("Folder does not exist!");
        }
    }

    @Test
    public void testForEveryCityInInputAReportIsWrittenToFile() throws IOException, ParseException {
        File output = new File(UpdateWeather.OUTPUT_PATH);
        emptyFolder(output);

        try {
            WeatherRequest[] weatherRequests = new WeatherRequest[] {new WeatherRequest("Tallinn", "EE", "metric")};
            List<JSONObject> jsonObjects = weatherUpdate.updateWeather(repoMock, weatherRequests);
            weatherUpdate.updateWeather(repoMock, weatherRequests);
            weatherUpdate.writeReportsToFile(jsonObjects);
            File[] files = output.listFiles();
            JSONObject object = jsonObjects.get(0);
            assertNotNull(files);
            assertEquals(1, files.length);
            assertEquals("Tallinn.txt", files[0].getName());
            assertEquals(13, (double) object.get("CurrentTemp"), 0.1);
        } catch (Exception e) {
            fail("Test failed because: " + e.getMessage());
        }
    }
}