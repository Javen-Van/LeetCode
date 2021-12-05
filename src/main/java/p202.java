import org.junit.Test;

/**
 * @author Javen
 * @create 2021-08-07
 * @Description
 */
public class p202 {

    @Test
    public void test() {
        boolean happy = isHappy(42);
        System.out.println(happy);
    }

    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);
        return slow == 1;
    }

    int getNext(int n) {
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}
