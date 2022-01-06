package swordToOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Javen
 * @create 2022-01-06
 * @Description 输入一个字符串，打印出该字符串中字符的所有排列。
 */
public class offer38 {

    List<String> res = new ArrayList<>();

    public String[] permutation(String s) {
        char[] str = s.toCharArray();
        dfs(0, str);
        return res.toArray(new String[0]);
    }

    public void dfs(int index, char[] str) {
        if (index == str.length) {
            res.add(new String(str));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < str.length; i++) {
            if (set.contains(str[i])) continue;
            set.add(str[i]);
            swap(i, index, str);
            dfs(index + 1, str);
            swap(i, index, str);
        }
    }

    public void swap(int i, int j, char[] arr) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
