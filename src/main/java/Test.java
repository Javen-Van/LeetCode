import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Javen
 * @create 2021-08-07
 * @Description
 */
public class Test {

    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left.push(i);
            else if (s.charAt(i) == '*') star.push(i);
            else {
                if (!left.isEmpty()) left.pop();
                else if (!star.isEmpty()) star.pop();
                else return false;
            }
        }
        while (!left.isEmpty() && !star.isEmpty()) {
            Integer l = left.pop();
            Integer starPop = star.pop();
            if (l > starPop) return false;
        }
        return left.isEmpty();
    }


    @org.junit.Test
    public void test2() {
//        String s1 = "hello";
//        String s2 = "world";
//        String s3 = "hello" + "world";
//        String s4 = s1 + "world";
//        String s5 = s1 + s2;
//        String s6 = (s1 + s2).intern();
//        System.out.println(s3 == s4);
//        System.out.println(s3 == s5);
//        System.out.println(s4 == s5);
//        System.out.println(s3 == s6);
        Map<String, String> map = new HashMap<>();
        String put = map.put("hello", "world");
        System.out.println(put);
    }

    @org.junit.Test
    public void test() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s5); // true
        System.out.println(s1 == s6); // false
        System.out.println(s1 == s6.intern()); // true
        System.out.println(s2 == s2.intern()); // false
    }

    public static void main(String[] args) {
        Communication communication = new Communication();
        Thread t1 = new Thread(communication);
        Thread t2 = new Thread(communication);
        t1.start();
        t2.start();

    }
}

class Communication implements Runnable {
    int i = 1;

    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (i <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + i++);
                } else break;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}