package fileWriter;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FileWriterTest {
    public static final String OUTPUT_PATH = new File(System.getProperty("user.dir"), "output_mock.txt").getPath();

    @Test
    public void testIfFileIsBeingWritten(){
        FileWriter fileWriterMock = mock(FileWriter.class);
        JSONObject mockJsonObject = new JSONObject();
        mockJsonObject.put("city", "Tallinn");
        verify(fileWriterMock).writeJsonIntoFile(mockJsonObject, OUTPUT_PATH);
    }


}
