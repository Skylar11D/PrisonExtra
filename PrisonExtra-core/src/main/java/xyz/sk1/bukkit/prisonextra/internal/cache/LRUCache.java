package xyz.sk1.bukkit.prisonextra.internal.cache;

import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;

    public LRUCache(int capacity) {
        super();
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
