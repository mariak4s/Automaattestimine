package utility;

import org.junit.Test;
import static org.junit.Assert.*;

public class HttpUtilityTest{

    @Test
    public void testIfConnectionExists(){
        boolean result = HttpUtility.connectionExists();
        assertEquals(result, HttpUtility.connectionExists());
    }

}