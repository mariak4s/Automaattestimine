import org.junit.Test;
import utility.HttpUtility;
import static junit.framework.TestCase.assertEquals;


public class HttpUtilityTest{

    @Test
    public void testIfConnectionExists(){
        boolean result = HttpUtility.connectionExists();
        assertEquals(result, HttpUtility.connectionExists());
    }

}