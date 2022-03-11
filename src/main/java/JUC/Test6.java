package JUC;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Javen
 * @create 2022-03-11
 * @Description
 */
public class Test6 {

    public static void main(String[] args) {
        MessageQueue mq = new MessageQueue(2);
        for (int i = 0; i < 4; i++) {
            int id = i;
            new Thread(() -> {
                mq.put(new Message(id, "value: " + id));
            }, "生产者" + id).start();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = mq.take();
            }
        }, "消费者").start();
    }
}

class MessageQueue {

    private Deque<Message> list = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("没有消息，等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message res = list.pollFirst();
            System.out.println("消费消息: " + res);
            list.notifyAll();
            return res;
        }
    }

    public void put(Message msg) {
        synchronized (list) {
            while (list.size() >= capacity) { // 队列是否已满
                try {
                    System.out.println("队列已满，等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产消息");
            list.offerLast(msg);
            list.notifyAll();
        }
    }
}

final class Message {

    private int id;
    private Object value;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }
}