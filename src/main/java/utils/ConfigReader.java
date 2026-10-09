
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = load();

    private ConfigReader() {

    }

    public static String get(String key) {
        String value = System.getProperty(key, PROPERTIES.getProperty(key));

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing config value for key: " + key
            );
        }

        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    private static Properties load() {
        Properties properties = new Properties();

        try (InputStream file = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (file == null) {
                throw new IllegalStateException(
                        "Config file not found on classpath: " + CONFIG_FILE
                );
            }

            properties.load(file);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to read config file: " + CONFIG_FILE, e
            );
        }

        return properties;
    }
}
