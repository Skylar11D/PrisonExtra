package xyz.sk1.bukkit.prisonextra.json.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.sk1.bukkit.prisonextra.json.JsonProcessorStrategy;

public class JacksonSerializer implements JsonProcessorStrategy {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String serialize(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(String string, Class<T> clazz) {
        try {
            return mapper.readValue(string, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
