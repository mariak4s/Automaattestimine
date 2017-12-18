package fileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static updateWeather.UpdateWeather.INPUT_FILE;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private static String cityName;
    private static JSONObject content;
    private static String countryCode;
    private static String units;

    @BeforeClass
    public static void setUpTests(){
        try {
            FileReader fileReader = new FileReader();
            cityName = "Tallinn";
            countryCode = "EE";
            units = "metric";
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

    @After
    public void tearDown() throws Exception {
    }
}
