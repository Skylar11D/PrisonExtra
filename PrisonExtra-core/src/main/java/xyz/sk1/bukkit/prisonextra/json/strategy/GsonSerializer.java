package xyz.sk1.bukkit.prisonextra.json.strategy;

import com.google.gson.Gson;
import xyz.sk1.bukkit.prisonextra.json.JsonProcessorStrategy;

public class GsonSerializer implements JsonProcessorStrategy {

    private Gson gson = new Gson();

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(String string, Class<T> clazz) {
        return gson.fromJson(string, clazz);
    }
}
