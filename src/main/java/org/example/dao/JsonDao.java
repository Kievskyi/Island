package org.example.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JsonDao implements Dao<Properties> {

    @Override
    public Properties load() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Properties properties;

        try {
            properties = new ObjectMapper().readValue(new File("src/main/resources/Properties.json"), Properties.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}