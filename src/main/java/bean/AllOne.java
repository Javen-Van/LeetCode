package bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Javen
 * @create 2022-03-16
 * @Description
 */
class AllOne {

    public Node head; // 头节点
    public Node tail; // 尾节点
    public Map<String, Node> map;

    public AllOne() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
    }

    public void inc(String key) {
        Node node;
        if (map.containsKey(key)) { // 已经存在key
            Node pre = map.get(key);
            if (pre.next == tail || pre.next.count > pre.count + 1) { // 节点为末节点，或下一个节点count太大
                node = new Node(pre.count + 1);
                node.insert(pre);
            } else node = pre.next;
            pre.removeKey(key); // 从上个节点中删除当前key
            pre.isEmpty();
        } else { // 不存在key
            if (head.next == tail || head.next.count > 1) {
                node = new Node(1);
                node.insert(head);
            } else node = head.next;
        }
        node.putKey(key);
        map.put(key, node); // 更新在map中对应的节点
    }

    public void dec(String key) {
        Node node = map.get(key), pre;
        if (node.count != 1) {
            if (node.pre == head || node.count > node.pre.count + 1) {
                pre = new Node(node.count - 1);
                pre.insert(node.pre);
            } else pre = node.pre;
            pre.putKey(key);
            node.removeKey(key);
            map.put(key, pre);
        } else map.remove(key);
        node.removeKey(key);
        node.isEmpty();
    }

    public String getMaxKey() {
        return head.next == tail ? "" : tail.pre.get();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.get();
    }

    public static class Node {
        public Set<String> keys;
        public int count;
        public Node pre;
        public Node next;

        public Node() {
            keys = new HashSet<>();
            count = 0;
        }

        public Node(int count) {
            this();
            this.count = count;
        }

        public void removeKey(String s) {
            keys.remove(s);
        }

        public void putKey(String s) {
            keys.add(s);
        }

        public void isEmpty() {
            if (keys.isEmpty()) {
                this.pre.next = this.next;
                this.next.pre = this.pre;
            }
        }

        // 将当前对象插入到node之后
        public void insert(Node node) {
            this.next = node.next;
            node.next.pre = this;
            node.next = this;
            this.pre = node;
        }

        public String get() {
            return keys.iterator().next();
        }
    }
}