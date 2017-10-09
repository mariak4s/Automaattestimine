package exceptions;

public class WeatherRepoNotFound extends Exception{

    public WeatherRepoNotFound(String errMsg){
        super("Error:" + errMsg);
    }

}
