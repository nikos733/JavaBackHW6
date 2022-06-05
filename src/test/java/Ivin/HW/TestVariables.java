package Ivin.HW;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestVariables {


    private static Properties var = new Properties();

    static {
        try {
            var.load(new FileInputStream("src/main/resources/HW3.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String hash() {
        return var.getProperty("hash");
    }
    public static String username() {
        return var.getProperty("username");
    }
    public static String apiKey() {
        return var.getProperty("apiKey");
    }
    public static String baseUrl() {
        return var.getProperty("url");
    }
    public static String complexSearch() {
        return var.getProperty("complexSearch");
    }
    public static String classifyCuisine() {
        return var.getProperty("classifyCuisine");
    }
}