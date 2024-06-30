package hot100;

import bean.ListNode;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

    /**
     * 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preFix = new int[n];
        preFix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preFix[i] = preFix[i - 1] + nums[i];
        }
        int min = 0, res = Integer.MIN_VALUE;
        for (int fix : preFix) {
            res = Math.max(res, fix - min);
            min = Math.min(min, fix);
        }
        return res;
    }

    /**
     * 合并区间-差分数组
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int[] diff = new int[10002];
        Set<Integer> set = new HashSet<>();
        for (int[] interval : intervals) {
            diff[interval[0]]++;
            diff[interval[1]]--;
            if (interval[0] == interval[1]) {
                set.add(interval[0]);
            }
        }
        List<int[]> resList = new ArrayList<>();
        int sum = 0, start = 0;
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] != 0 && sum == 0) {
                start = i;
            }
            sum += diff[i];
            if (diff[i] != 0 && sum == 0) {
                resList.add(new int[]{start, i});
                continue;
            }
            if (sum == 0 && set.contains(i)) {
                resList.add(new int[]{i, i});
            }
        }
        int[][] res = new int[resList.size()][2];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    /**
     * 合并区间-排序
     *
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<int[]> resList = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for (int j = 1; j < intervals.length; j++) {
            int l = intervals[j][0], r = intervals[j][1];
            if (l > end) {
                // 不可合并
                resList.add(new int[]{start, end});
                start = l;
                end = r;
            } else {
                // 可以合并，更新右端点值
                end = Math.max(end, r);
            }
        }
        resList.add(new int[]{start, end});
        return resList.toArray(new int[resList.size()][2]);
    }

    /**
     * 轮转数组
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < k) {
                res[i] = nums[n - k + i];
            } else {
                res[i] = nums[i - k];
            }
        }
        System.arraycopy(res, 0, nums, 0, n);
    }

    /**
     * 原地修改
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * p238 除自身以外数组的乘积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] preFix = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                preFix[i] = nums[i];
            } else {
                preFix[i] = preFix[i - 1] * nums[i];
            }
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i] *= nums[i + 1];
        }
        int pre = 1;
        for (int i = 0; i < nums.length; i++) {
            int temp = preFix[i];
            preFix[i] = pre * (i == nums.length - 1 ? 1 : nums[i + 1]);
            pre = temp;
        }
        return preFix;
    }

    /**
     * p41 缺失的第一个正数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n && nums[num - 1] > 0) {
                nums[num - 1] *= -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * p73 矩阵置0
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean x0 = matrix[0][0] == 0, y0 = x0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) x0 = true;
                    if (j == 0) y0 = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    break;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (x0) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (y0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * p54 螺旋矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = n - 1, u = 0, b = m - 1;
        int i = 0, j = -1, round = 0;
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        while (l <= r && u <= b) {
            i += dx[round];
            j += dy[round];
            res.add(matrix[i][j]);
            if (round == 0 && j == r) {
                u++;
                round++;
            }
            if (round == 1 && i == b) {
                r--;
                round++;
            }
            if (round == 2 && j == l) {
                b--;
                round++;
            }
            if (round == 3 && i == u) {
                l++;
                round++;
            }
            round %= 4;
        }
        return res;
    }

    /**
     * p48 旋转图像
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int a = matrix[i][j], b = matrix[j][n - 1 - i], c = matrix[n - 1 - i][n - 1 - j], d = matrix[n - 1 - j][i];
                matrix[i][j] = d;
                matrix[j][n - 1 - i] = a;
                matrix[n - 1 - i][n - 1 - j] = b;
                matrix[n - 1 - j][i] = c;
            }
        }
    }

    /**
     * p240 搜索二维矩阵 - 二分查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }

    /**
     * p160 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode dummyA = headA, dummyB = headB;
        while (dummyA != null || dummyB != null) {
            if (dummyA == dummyB) return dummyA;
            dummyA = dummyA == null ? headB : dummyA.next;
            dummyB = dummyB == null ? headA : dummyB.next;
        }
        return null;
    }

    /**
     * p206 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode listNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    /**
     * p234 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            if (list.get(l) != list.get(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    /**
     * p141 环形链表
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        } while (fast != null && fast.next != null);
        return false;
    }

    /**
     * p142 环形链表2
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head, res = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        } while (fast != null && fast.next != null);
        if (fast == null || fast.next == null) return null;
        while (slow != res) {
            slow = slow.next;
            res = res.next;
        }
        return res;
    }

    /**
     * p21 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(), res = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                dummy.next = list1;
                list1 = list1.next;
            } else {
                dummy.next = list2;
                list2 = list2.next;
            }
            dummy = dummy.next;
        }
        dummy.next = list1 == null ? list2 : list1;
        return res.next;
    }

}
