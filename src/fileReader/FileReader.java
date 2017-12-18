package fileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;


public class FileReader {

    public JSONArray readFile(String filename) {
        JSONParser jsonParser = new JSONParser();
        JSONArray fileContent = null;
        try {
            java.io.FileReader fileReader = new java.io.FileReader(filename);
            fileContent = (JSONArray) jsonParser.parse(fileReader);
        }  catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

}
