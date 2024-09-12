package xyz.sk1.bukkit.prisonextra.internal.cache;

public interface CacheRegistry {

    Cache<?, ?> getCache(String key);

    void createCache(String key, int capacity);
    void removeCache(String key);

}
