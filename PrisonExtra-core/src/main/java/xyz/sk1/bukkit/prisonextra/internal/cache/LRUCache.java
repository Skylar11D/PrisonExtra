package xyz.sk1.bukkit.prisonextra.internal.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
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
        }

        if(size() >= capacity){
            removeLast();
        }
        node = new Node<>(key, value);
        map.put(key, node);
        addNode(node);

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

    private void removeNode(Node<?,?> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNode(Node<?,?> node) {
        node.prev = head; //i got mental issues understanding this
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeLast(){
        Node<?, ?> node = tail.prev;
        removeNode(node);
        map.remove(node.key);
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public int size() {
        return 0;
    }
}
