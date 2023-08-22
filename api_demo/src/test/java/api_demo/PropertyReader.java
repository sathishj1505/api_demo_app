package api_demo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String getValueFromProperties(String input){
        String value = "";
        try {
            InputStream inputStream = new FileInputStream("src/test/resources.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            value = properties.getProperty(input);

        } catch (Exception e){
        }
        return value;
    }
}
