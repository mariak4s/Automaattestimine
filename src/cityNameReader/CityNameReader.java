package cityNameReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openweathermap.api.model.forecast.City;

import java.io.File;
import java.util.Scanner;

import fileReader.FileReader;

public class CityNameReader {
    private static Scanner scanner;

    private static String getCityNameFromInput(){
        System.out.println("Read city name from: [C]onsole | [F]ile. \n");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("F")){
            return getCityNameFromFile();
        } else if (input.equalsIgnoreCase("C")){
            return getCityNameFromConsole();
        } else {
            System.out.println("Incorrect input, city name will be read from the fileReader.");
            return getCityNameFromFile();
        }
    }

    private static String getCityNameFromFile() {
        System.out.println("City name is being read from fileReader:\n");
        File inputFile = new File(System.getProperty("user.dir"), "input.txt");
        JSONArray inputJson = new FileReader().readFile(inputFile.getPath());
        JSONObject city = (JSONObject) inputJson.get(0);
        return (String) city.get("city");
    }


    private static String getCityNameFromConsole(){
        System.out.println("City name is being read from console:\n");
        System.out.println("Enter city name: ");
        scanner = new Scanner(System.in);
        String cityNameFromConsole = scanner.nextLine();
        return cityNameFromConsole;
    }

    public static void main(String [] args) {
        City city = new City();
        String cityName = getCityNameFromInput();
        city.setName(cityName);
        System.out.println(city.toString());

    }
}
