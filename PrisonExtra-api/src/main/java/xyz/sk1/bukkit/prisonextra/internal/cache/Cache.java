package xyz.sk1.bukkit.prisonextra.internal.cache;

import java.util.Map;

public interface Cache<K, V> {

    boolean removeEldestEntry(Map.Entry<K, V> eldest);

    void put(K key, V value);

    V get(K key);

    void remove(K key);

    void clear();

    int size();

}