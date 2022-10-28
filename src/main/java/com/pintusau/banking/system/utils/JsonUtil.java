package com.pintusau.banking.system.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pintusau.banking.system.entities.AbstractEntity;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JsonUtil {

  public static String serialize(Object object) {
    String jsonValue = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      jsonValue = mapper.writeValueAsString(object);
    } catch (IOException e) {
      log.error("Serialization failed: {}", e.getMessage());
    }
    return jsonValue;
  }

  public static Object deserialize(String json, Class<? extends AbstractEntity> classObject) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(json, classObject);
    } catch (IOException e) {
      log.error("Deserialization failed: {}", e.getMessage());
    }
    return null;
  }
}
