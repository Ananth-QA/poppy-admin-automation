//package utils;
//
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static Properties prop;
//
//    static {
//        try {
//            prop = new Properties();
//            FileInputStream fis =
//                    new FileInputStream("src/main/resources/config.properties");
//            prop.load(fis);
//        } catch (Exception e) {
//            throw new RuntimeException("❌ Unable to load config.properties");
//        }
//    }
//
//    public static String get(String key) {
//        return prop.getProperty(key);
//    }
//
//
//}



package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            prop = new Properties();
            FileInputStream fis =
                    new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to load config.properties", e);
        }
    }

    /**
     * Get config value by key.
     * Fails fast if key is missing or empty.
     */
    public static String get(String key) {

        String value = prop.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "❌ Missing key in config.properties → " + key
            );
        }

        value = value.trim();

        if (value.isEmpty()) {
            throw new RuntimeException(
                    "❌ Empty value for key in config.properties → " + key
            );
        }

        return value;
    }
}


