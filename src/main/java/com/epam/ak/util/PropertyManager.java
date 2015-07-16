package com.epam.ak.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private final Properties properties;

    public PropertyManager(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
