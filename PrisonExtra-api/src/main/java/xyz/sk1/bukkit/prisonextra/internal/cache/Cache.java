package xyz.sk1.bukkit.prisonextra.internal.cache;

import java.util.Map;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public interface Cache<K, V> extends Iterable<Map.Entry<K, V>> {

    void put(K key, V value);

    V get(K key);

    void remove(K key);


    int size();

}