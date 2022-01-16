package JVM;

import org.junit.Test;

import java.util.Random;

/**
 * @author Javen
 * @create 2022-01-15
 * @Description
 */
public class StringIntern {

    public static void main(String[] args) {
        String s1 = new String("a");
        s1.intern();
        String s2 = "a";
        System.out.println(s1 == s2); // false

        String s3 = new String("a") + new String("b");
        s3.intern();
        String s4 = "ab";
        System.out.println(s3 == s4); // true
    }

    @Test
    public void test() {
        String s3 = new String("a") + new String("b");
        String s4 = "ab"; // 在字符串常量池中生成对象"ab"
        s3.intern(); // 没有实际作用
        System.out.println(s3 == s4); // false
    }
}
