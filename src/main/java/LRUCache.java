import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */
public class LRUCache<K, V> {

    public LRUCache() {

    }

    class Node {
        Node pre, next;
        K key;
        V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Node() {

        }
    }

    private Map<K, Node> map = new HashMap<>();

    private Node head, tail;

    int size;

    int cap;

    public LRUCache(int cap) {
        this.cap = cap;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            Node value = map.get(key);
            // 移到队首
            moveToHead(value);
            return value.val;
        }
        return null;
    }

    public void put(K key, V value) {
        // 存在当前key
        if (map.containsKey(key)) {
            // 更新value值
            Node node = map.get(key);
            node.val = value;
            // 移到队首
            moveToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            // 添加到队首
            method(node);
            // 容量大小校验
            size++;
            if (size > cap) {
                // 移除尾部节点
                Node tailNode = removeTail();
                map.remove(tailNode.key);
                size--;
            }
        }
    }

    private void moveToHead(Node node) {
        // 当前节点移除
        removeNode(node);
        method(node);
    }

    private void method(Node node) {
        // 当前节点移动到头节点
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private Node removeTail() {
        Node node = tail.pre;
        removeNode(node);
        return node;
    }

    private void removeNode(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
}
