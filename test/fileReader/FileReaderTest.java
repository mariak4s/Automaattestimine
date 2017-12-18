package fileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static updateWeather.UpdateWeather.INPUT_FILE;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private static String cityName;
    private static JSONObject content;
    private static String countryCode;
    private static String units;
    private static FileReader fileReaderMock;

    @BeforeClass
    public static void setUpTests(){
        try {
            FileReader fileReader = new FileReader();
            cityName = "Tallinn";
            countryCode = "EE";
            units = "metric";
            fileReaderMock = mock(FileReader.class);
            File f = new File(INPUT_FILE);
            JSONArray randomJsonArray = new JSONArray();
            JSONObject randomJsonObject = new JSONObject();
            randomJsonObject.put("city", "Tallinn");
            randomJsonObject.put("countryCode", "EE");
            randomJsonObject.put("units", "metric");
            randomJsonArray.add(randomJsonObject);
            if (!f.exists() && f.isDirectory()) {
                when(fileReader.readFile(any(String.class))).thenReturn(randomJsonArray);
            }
            JSONArray fileContent = fileReader.readFile(INPUT_FILE);
            content = (JSONObject) fileContent.get(0);
        } catch (Exception e){
            fail("All tests failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReaderRetunsCorrectCity(){
        try {
            assertEquals(cityName, content.get("city"));
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReaderRetunsCorrectCountryCode(){
        try {
            assertEquals(countryCode, content.get("countryCode"));
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfReaderRetunsCorrectUnits(){
        try {
            assertEquals(units, content.get("units"));
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @Test
    public void testIfFileIsRead(){
        String INPUT_FILE = new File(System.getProperty("user.dir"), "input_mock.txt").getPath();
        try {
            verify(fileReaderMock).readFile(INPUT_FILE);
        } catch (Exception e){
            fail("Test failed because: " + e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }
}
