package fileWriter;

import org.json.simple.JSONObject;

import java.io.IOException;

public class FileWriter {
    public static void writeJsonIntoFile(JSONObject jsonObject, String fileName){
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("cityName", "Tallinn");
        FileWriter.writeJsonIntoFile(object, "/Users/Maria/IdeaProjects/WeatherApp/Automaattestimine/output.txt");
    }
}
