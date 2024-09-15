package xyz.sk1.bukkit.prisonextra.json;

public interface JsonProcessorStrategy {

    String serialize(Object object);

    <T> T deserialize(String string, Class<T> clazz);

}
