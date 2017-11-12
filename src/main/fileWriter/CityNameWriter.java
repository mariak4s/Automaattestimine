package fileWriter;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CityNameWriter {
    public static void writeJsonIntoFile(JSONObject jsonObject){
        try {
            FileWriter fileWriter = new FileWriter("/Users/Maria/IdeaProjects/WeatherApp/Automaattestimine/output.txt");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        } catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        JSONObject object = new JSONObject();
        object.put("cityName", "Tallinn");
        fileWriter.writeJsonToFile(object);
    }
}
