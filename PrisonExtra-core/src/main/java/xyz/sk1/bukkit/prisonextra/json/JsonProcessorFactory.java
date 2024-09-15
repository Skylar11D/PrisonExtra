package xyz.sk1.bukkit.prisonextra.json;

import xyz.sk1.bukkit.prisonextra.json.strategy.GsonSerializer;
import xyz.sk1.bukkit.prisonextra.json.strategy.JacksonSerializer;

import java.util.Optional;

public class JsonProcessorFactory {

    public Optional<JsonProcessorStrategy> createStrategy(JsonStrategy strategy){

        switch (strategy){
            case GSON: {
                return Optional.of(new GsonSerializer());
            }

            case JACKSON: {
                return Optional.of(new JacksonSerializer());
            }
        }

        return Optional.empty();
    }

}
