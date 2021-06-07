package com.pintusau.banking.system.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pintusau.banking.system.entities.AbstractEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil<T extends AbstractEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public String serialize(T t) {
        String jsonValue = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonValue = mapper.writeValueAsString(t);
        }
        catch (IOException e) {
            LOGGER.error("Serialization failed: {}",  e.getMessage());
        }
        return jsonValue;
    }

    public T deserialize(String json, Class<T> type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, type);
        }
        catch (IOException e) {
            LOGGER.error("Deserialization failed: {}",  e.getMessage());
        }
        return null;
    }
}