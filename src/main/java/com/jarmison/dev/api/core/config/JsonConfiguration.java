package com.jarmison.dev.api.core.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.Map;

public class JsonConfiguration {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new Jdk8Module());
    }

    public static String json(Object object) {
        try {
            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}