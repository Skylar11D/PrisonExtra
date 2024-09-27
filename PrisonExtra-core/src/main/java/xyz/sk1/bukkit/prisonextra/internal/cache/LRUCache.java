package xyz.sk1.bukkit.prisonextra.internal.cache;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class LRUCache<K, V> implements Cache<K, V> {

    private int capacity;
    private int size;
    private final Map<K, Node<?, ?>> map;
    private final Node<K, V> head;
    private final Node<K, V> tail;

    private static class Node<K, V> {

        K key;
        V value;
        Node<?, ?> prev;

        Node<?, ?> next;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = (Node<K, V>) map.get(key);

        if(node != null){
            node.value = value;
            removeNode(node); //removes it from its position
            addNode(node); //place it in the head
            return;
        }

        if(size() >= capacity){
            removeLast();
        }
        node = new Node<>(key, value);
        map.put(key, node);
        addNode(node);
        size++;

    }

    @Override
    public V get(K key) {
        Node<?, ?> node = map.get(key);

        if(node == null){
            return null;
        }

        moveToFront(node);

        return (V) node.value;
    }

    private void moveToFront(Node<?,?> node) {
        removeNode(node); //removes node from the tail
        addNode(node); //add to the head
    }

    //linking neighbors and removing the node
    private void removeNode(Node<?,?> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //i got mental issues understanding this
    private void addNode(Node<?,?> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node; //making the tail point to the new head
        head.next = node;
    }

    private void removeLast(){
        Node<?, ?> node = tail.prev;
        removeNode(node);
        map.remove(node.key);
        size--;
    }

    @Override
    public void remove(K key) {
        Node<?, ?> node = map.remove(key);

        if (node == null) {
            return;
        }
        removeNode(node);
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull Iterator<Map.Entry<K, V>> iterator() {
        return new CacheIterator();
    }

    private class CacheIterator implements Iterator<Map.Entry<K, V>> {
        private Node<K, V> current = (Node<K, V>) head.next;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Map.Entry<K, V> next() {
            Node<K, V> node = current;
            current = (Node<K, V>) current.next;
            return new Map.Entry<K, V>() {
                @Override
                public K getKey() {
                    return node.key;
                }

                @Override
                public V getValue() {
                    return node.value;
                }

                @Override
                public V setValue(V value) {
                    V oldValue = node.value;
                    node.value = value;
                    return oldValue;
                }
            };
        }
    }

}
