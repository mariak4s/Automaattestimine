package fileReader;



import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class CityNameReader {
    private static Scanner scanner;

    private static String getCityNameFromFile(){
        System.out.println("City name is being read from file:\n");
        try {
            Scanner fileIn = new Scanner(new File("/Users/Maria/IdeaProjects/WeatherApp/Automaattestimine"));
            String cityNameFromFile = fileIn.nextLine();
            return cityNameFromFile;
        } catch (FileNotFoundException e){
            return ("File not found: " + e);
        }
    }

    private static String getCityNameFromConsole(){
        System.out.println("City name is being read from console:\n");
        System.out.println("Enter city name: ");
        scanner = new Scanner(System.in);
        String cityNameFromConsole = scanner.nextLine();
        return cityNameFromConsole;
    }

    private static String getCityNameFromInput(){
        System.out.println("Read city name from: [C]onsole | [F]ile. \n");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("R")){
            return getCityNameFromFile();
        } else if (input.equalsIgnoreCase("C")){
            return getCityNameFromConsole();
        } else {
            System.out.println("Incorrect input, city name will be read from the file.");
            return getCityNameFromFile();
        }
    }

    public static void main(String[] args){
        String cityName = getCityNameFromInput();
        System.out.print(cityName);
    }
}
