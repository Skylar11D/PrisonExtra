package xyz.sk1.bukkit.prisonextra.internal.cache;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public interface CacheRegistry {

    Cache<?, ?> getCache(String key);

    void createCache(String key, int capacity);
    void removeCache(String key);

}
