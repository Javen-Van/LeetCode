package bean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Javen
 * @create 2022-03-13
 * @Description
 */
public class LRU<K, V> {

    private int capacity = 1024;
    private Map<K, V> table = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > capacity;
        }
    };

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public LRU() {
    }

    public V get(K key) {
        return table.get(key);
    }

    public void put(K key, V value) {
        table.put(key, value);
    }

    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(3);
        lru.put(1, 2);
        lru.put(2, 3);
        lru.put(3, 4);
        lru.get(1);
        lru.put(4, 5); // 3,1,4
        System.out.println(lru.get(2));

    }

}
