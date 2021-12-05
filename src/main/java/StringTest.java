import bean.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.DoubleStream;

import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * @author Javen
 * @create 2021-08-20
 * @Description
 */
public class StringTest {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringTest that = (StringTest) o;

        if (str != null ? !str.equals(that.str) : that.str != null) return false;
        return Arrays.equals(ch, that.ch);
    }

    @Override
    public int hashCode() {
        int result = str != null ? str.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(ch);
        return result;
    }

    @Test
    public void t2() {
        Random random = new Random();
        double x = random.nextDouble();
        System.out.println(x);
    }

    public void change(String str, char[] ch) {
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");//
        System.out.println(ex.ch);
    }

    @Test
    public void test() {
        int[] target = new int[]{6, -9};
        int[][] ghosts = new int[][]{{1, 8}, {-9, 0}, {1, 3}, {4, 3}, {-7, -6}};
        int route = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            if ((Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1])) <= route) {
                System.out.println(false);
            }
        }
        System.out.println(true);
    }

    @Test
    public void test3(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");
        set.add(p1);
        set.add(p2);
        p1.name = "CC";
        set.remove(p1);
        System.out.println(set); // 2个元素
        set.add(new Person(1001,"CC")); // 可以添加
        System.out.println(set);
        set.add(new Person(1001,"AA")); // 可以添加
        System.out.println(set);
    }
}

class ForTest {
    public static void main(String[] args) {
        String[] str = new String[5];
        for (String myStr : str) {
            myStr = "atguigu";
            System.out.println(myStr);
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(new Integer(2));
        System.out.println(8+(8>>1));
    }


}