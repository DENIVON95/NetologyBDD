package ru.netology.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URL;

public class JsonParser {
    public static <T> T getObjectFromJsonFile(Class<T> tClass, String filePath) {
        try {
            return new ObjectMapper().readValue(new File(filePath), tClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
