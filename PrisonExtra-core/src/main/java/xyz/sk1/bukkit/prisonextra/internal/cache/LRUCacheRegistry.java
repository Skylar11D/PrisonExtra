package xyz.sk1.bukkit.prisonextra.internal.cache;

import xyz.sk1.bukkit.prisonextra.exception.KeyExistsCacheException;
import xyz.sk1.bukkit.prisonextra.exception.NoSuchKeyCacheException;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheRegistry<K, V> implements CacheRegistry {

    private Map<String, Cache<K, V>> cacheMap;

    public LRUCacheRegistry(){
        this.cacheMap = new HashMap<>();
    }

    @Override
    public Cache<K, V> getCache(String mapKey) {
        return cacheMap.get(mapKey);
    }

    @Override
    public void createCache(String key, int capacity) {
        if(cacheMap.containsKey(key)){
            throw new KeyExistsCacheException(key);
        }

        Cache<K, V> cache = new LRUCache<>(capacity);
        cacheMap.put(key, cache);
    }

    @Override
    public void removeCache(String key) {
        if(!cacheMap.containsKey(key))
            throw new NoSuchKeyCacheException(key);
        cacheMap.remove(key);
    }

}
