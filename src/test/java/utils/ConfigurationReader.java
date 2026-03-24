package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    public static String getProperty(String key) {

        File file = new File("src/test/resources/configuration.properties");
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            System.out.println("Wrong file path provided for configurations file");
            throw new RuntimeException();
        }
        return properties.getProperty(key);
    }

}