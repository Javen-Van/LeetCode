package exam.xiecheng;


import java.util.*;

public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), k = sc.nextInt();
            String s = sc.next();
            System.out.println(method(s, k));
        }
    }

    public static int method(String s, int k) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, count = 0, res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, i);
            if (map.size() > k) {
                res = Math.max(res, count);
                start = map.get(s.charAt(start));
                List<Character> set = new ArrayList<>(map.keySet());
                for (Character character : set) {
                    if (map.containsKey(character) && map.get(character) < start) map.remove(character);
                }
                if (map.size() > k) {
                    map.remove(s.charAt(start));
                    start++;
                }
                count = i - start + 1;
            } else {
                count++;
            }
        }
        return Math.max(count, res);
    }
}
