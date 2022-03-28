package bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @create 2022-03-28
 * @Description p146 LRU缓存
 */
class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        afterAccess(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.getOrDefault(key, new Node(key, value));
        node.value = value;
        map.put(key, node);
        afterAccess(node);
        if (map.size() > capacity) removeEldest(); // 移除节点
    }

    private void afterAccess(Node node) {
        if (node.pre != null) { // 删除节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        // 放到队尾
        node.pre = tail.pre;
        node.next = tail;
        tail.pre.next = node;
        tail.pre = node;
    }

    private void removeEldest() {
        Node node = head.next;
        head.next = node.next;
        node.next.pre = head;
        map.remove(node.key);
    }

    public static class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
