package hot100;

import java.util.*;

public class Solution {

    // 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{i, map.get(diff)};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[][] count = new int[strs.length][26];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                count[i][strs[i].charAt(j) - 'a']++;
            }
        }
        for (int i = 0; i < strs.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                sb.append((char) ('a' + j)).append(count[i][j]);
            }
            List<String> list = map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(strs[i]);
            map.put(sb.toString(), list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 最长连续序列
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int len = 1, curNum = num + 1;
                while (set.contains(curNum)) {
                    len++;
                    curNum++;
                }
                res = Math.max(res, len);
            }
        }
        return res;
    }

    /**
     * 移动0
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     * <p>
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int[] preFix = new int[nums.length + 1];
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            preFix[i + 1] = preFix[i] + nums[i];
            int minus = preFix[i + 1] - k;
            if (count.containsKey(minus)) {
                res += count.get(minus);
            }
            Integer c = count.getOrDefault(preFix[i + 1], 0);
            count.put(preFix[i + 1], c + 1);
        }
        return res;
    }

    /**
     * 滑动窗口最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 队列记录的是下标索引
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            offer(deque, i, nums);
        }
        res[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            offer(deque, i, nums);
            // 如果队首元素为窗口弹出元素，则需要弹出队首
            if (i - k == deque.peekFirst()) {
                deque.pollFirst();
            }
            // 队首元素始终为窗口内最大值的下标索引
            res[i - k + 1] = nums[deque.peekFirst()];
        }
        return res;
    }

    private void offer(Deque<Integer> deque, int idx, int[] num) {
        // 单调队列，入队元素如果大于队尾元素，则弹出队尾，保持单调递减
        while (!deque.isEmpty() && num[deque.peekLast()] < num[idx]) {
            deque.pollLast();
        }
        deque.offerLast(idx);
    }

    /**
     * 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> tCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCount.put(t.charAt(i), tCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 滑动窗口当前的左右边界
        int left = 0, right = 0;
        // 结果的左右边界
        int ansLeft = -1, ansRight = s.length();
        while (right < s.length()) {
            char c = s.charAt(right);
            // 将当前遍历到的记录到右边界
            sCount.put(c, sCount.getOrDefault(c, 0) + 1);
            // 判断是否覆盖
            while (isCover(sCount, tCount)) {
                if (right - left < ansRight - ansLeft) {
                    ansLeft = left;
                    ansRight = right;
                }
                char leftChar = s.charAt(left++);
                // 将左边界移出
                sCount.put(leftChar, sCount.get(leftChar) - 1);
            }
            right++;
        }
        return ansLeft < 0 ? "" : s.substring(ansLeft, ansRight + 1);
    }

    private boolean isCover(Map<Character, Integer> sCount, Map<Character, Integer> tCount) {
        for (char key : tCount.keySet()) {
            if (!sCount.containsKey(key) || sCount.get(key) < tCount.get(key)) {
                return false;
            }
        }
        return true;
    }

}
