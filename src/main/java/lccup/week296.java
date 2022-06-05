package lccup;

import org.junit.Test;

import java.util.*;

public class week296 {

    // Q1
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        int[] tree = new int[n << 1];
        if ((n << 1) - n >= 0) System.arraycopy(nums, 0, tree, n, (n << 1) - n);
        for (int i = n - 1; i > 0; i--) {
            int left = tree[2 * i], right = tree[2 * i + 1];
            if (i % 2 == 0 || i == 1) tree[i] = Math.min(left, right);
            else tree[i] = Math.max(left, right);
        }
        return tree[1];
    }

    @Test
    public void test() {
        int[] nums = {70, 39, 21, 22};
        System.out.println(minMaxGame(nums));
    }

    // Q2
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 1, start = nums[0];
        for (int num : nums) {
            if (num > start + k) {
                count++;
                start = num;
            }
        }
        return count;
    }

    // Q3
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
        for (int[] operation : operations) {
            int origin = operation[0], target = operation[1];
            for (int index : map.get(origin)) {
                nums[index] = target;
                List<Integer> change = map.getOrDefault(target, new ArrayList<>());
                change.add(index);
                map.put(target, change);
            }
            map.remove(origin);
        }
        return nums;
    }
}

class TextEditor {

    StringBuilder sb = new StringBuilder();
    int point = 0;

    public TextEditor() {

    }

    public void addText(String text) {
        sb.insert(point, text);
        point += text.length();
    }

    public int deleteText(int k) {
        int res = Math.min(k, point);
        sb.delete(point - res, point);
        point -= res;
        return res;
    }

    public String cursorLeft(int k) {
        int move = Math.min(k, point);
        point -= move;
        return sb.substring(Math.max(0, point - 10), point);
    }

    public String cursorRight(int k) {
        point = Math.min(point + k, sb.length());
        return sb.substring(Math.max(0, point - 10), point);
    }
}
