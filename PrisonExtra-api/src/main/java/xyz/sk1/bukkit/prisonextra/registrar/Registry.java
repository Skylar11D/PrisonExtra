package xyz.sk1.bukkit.prisonextra.registrar;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public abstract class Registry<K, V> {

    @Getter
    private final Map<K, V> registry;

    protected Registry() {
        this.registry = new HashMap<>();;
    }

    public abstract void register(K key, V value);

    public abstract void unregister(K key);

    public abstract V getType(K key);

}
