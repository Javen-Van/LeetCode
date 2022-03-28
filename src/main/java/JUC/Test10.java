package JUC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Javen
 * @create 2022-03-27
 * @Description
 */
public class Test10 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        i.incrementAndGet();  // ++i -> 1
        i.getAndIncrement();  // i++ -> 0
        i.decrementAndGet();  // --i -> -1
        i.getAndDecrement();  // i-- -> 0
        i.addAndGet(5); // 5
        i.getAndAdd(5); // 0
        i.updateAndGet(operand -> operand * 10); // 可以是任意形式的运算
        AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
        String pre, next;
        int preStamp;
        do {
            pre = ref.getReference();
            preStamp = ref.getStamp();
            next = "B";
        } while (ref.compareAndSet(pre, next, preStamp, preStamp + 1));
        AtomicReference<Outer.Inner> rr = new AtomicReference<>(new Outer.Inner(10));
        Outer.Inner inner = new Outer.Inner(10);
        System.out.println(rr.compareAndSet(inner, new Outer.Inner(11)));
    }

    public static class Outer {
        private Inner value;

        public Outer(Inner value) {
            this.value = value;
        }

        public Inner getValue() {
            return this.value;
        }

        private static class Inner {
            public int key;

            public Inner(int i) {
                this.key = i;
            }
        }
    }
}

class Account<T extends Money> {
    private AtomicReference<T> balance;

    public Account(T balance) {
        this.balance = new AtomicReference<>(balance);
    }

    public T getBalance() {
        return balance.get();
    }

    public void withdraw(T money) {
        while (true) {
            T pre = balance.get();
            T next = pre.subtract(money);
            if (balance.compareAndSet(pre, next)) break;
        }
    }
}

interface Money {
    <T extends Money> T subtract(T money);
}