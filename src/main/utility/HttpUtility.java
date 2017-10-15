package utility;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.IOException;

public class HttpUtility{
    public static boolean connectionExists() {
        try{
            final URL url = new URL("http://www.google.com");
            final URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (MalformedURLException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            return false;
        }
    }
}


