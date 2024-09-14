package xyz.sk1.bukkit.prisonextra.internal.cache;

import xyz.sk1.bukkit.prisonextra.exception.KeyExistsCacheException;
import xyz.sk1.bukkit.prisonextra.exception.NoSuchKeyCacheException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class LRUCacheRegistry<K, V> implements CacheRegistry {

    private Map<String, Cache<K, V>> cacheMap;

    public LRUCacheRegistry(){
        this.cacheMap = new HashMap<>();
    }

    /**
     *
     * @param key the key to access the respective cache
     * @return the cache nodes assigned to the key
     */
    @Override
    public Cache<K, V> getCache(String key) {
        return cacheMap.get(key);
    }

    /**
     * Creates an LRU Cache for the specified key
     * @param key assign a key for a cache nodes
     * @param capacity limits the size of the nodes
     */
    @Override
    public void createCache(String key, int capacity) {
        if(cacheMap.containsKey(key)){
            throw new KeyExistsCacheException(key);
        }

        Cache<K, V> cache = new LRUCache<>(capacity);
        cacheMap.put(key, cache);
    }

    /**
     * Removes a cache belonging to a key in the LRU Cache Map
     * @param key the respective key for the map
     */
    @Override
    public void removeCache(String key) {
        if(!cacheMap.containsKey(key))
            throw new NoSuchKeyCacheException(key);
        cacheMap.remove(key);
    }

}
