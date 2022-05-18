package lccup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class week293 {

    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        int index = 0;
        for (int i = 1; i < words.length; i++) {
            if (!isAnagram(words[i], words[index])) {
                res.add(words[index]);
                index = i;
            }
        }
        res.add(words[index]);
        return res;
    }

    public boolean isAnagram(String a, String b) {
        if (a.equals(b)) return true;
        if (a.length() != b.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }

    // Q2
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = Math.max(special[0] - bottom, top - special[special.length - 1]);
        for (int i = 1; i < special.length; i++) {
            max = Math.max(max, special[i] - special[i - 1] - 1);
        }
        return max;
    }

    // Q3
    public int largestCombination(int[] candidates) {
        int max = 0;
        for (int i = 0; i < 25; i++) {
            int count = 0;
            for (int j = 0; j < candidates.length; j++) {
                if ((candidates[j] & 1) == 1) count++;
                candidates[j] >>= 1;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

class CountIntervals {

    int count = 0;
    TreeMap<Integer, Integer> tree = new TreeMap<>();

    public CountIntervals() {
    }

    public void add(int left, int right) {
        Integer L = tree.floorKey(right);
        int end = right;
        while (null != L && tree.get(L) >= left) {
            left = Math.min(left, L);
            right = Math.max(right, tree.get(L));
            count -= tree.get(L) - L + 1;
            tree.remove(L);
            L = tree.floorKey(end);
        }
        count += right - left + 1;
        tree.put(left, right);
    }

    public int count() {
        return this.count;
    }
}