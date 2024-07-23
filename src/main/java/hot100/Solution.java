package hot100;

import bean.ListNode;
import bean.TreeNode;

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
     * p2 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), node = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            ListNode listNode = new ListNode(sum % 10);
            node.next = listNode;
            node = listNode;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            ListNode listNode = new ListNode(sum % 10);
            node.next = listNode;
            node = listNode;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum / 10;
            ListNode listNode = new ListNode(sum % 10);
            node.next = listNode;
            node = listNode;
            l2 = l2.next;
        }
        if (carry != 0) {
            node.next = new ListNode(carry);
        }
        return dummy.next;
    }

    /**
     * 删除链表的倒数第N个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(), tail = head, cur = head, pre = dummy;
        dummy.next = head;
        while (tail != null) {
            tail = tail.next;
            if (n > 0) {
                n--;
                continue;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        cur.next = null;
        return dummy.next;
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

    /**
     * p24 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        while (head != null && head.next != null) {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            pre.next = temp;
            pre = head;
            head = head.next;
        }
        return dummy.next;
    }

    /**
     * p25 k个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(), pre = dummy, cur = dummy;
        dummy.next = head;
        while (cur.next != null) {
            for (int i = 0; i < k; i++) {
                cur = cur.next;
                if (cur == null) return dummy.next;
            }
            ListNode start = pre.next;
            ListNode temp = cur.next;
            cur.next = null;
            pre.next = reverseList(start);
            start.next = temp;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    /**
     * p138 随机链表的复制
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        cur = head;
        while (cur != null) {
            Node newNode = cur.next;
            newNode.random = cur.random == null ? null : cur.random.next;
            cur = newNode.next;
        }
        Node res = head.next;
        while (head != null) {
            Node newNode = head.next, next = newNode.next;
            newNode.next = next == null ? null : next.next;
            head.next = next;
            head = next;
        }
        return res;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * p148 排序链表
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        ListNode left = sortList(head), right = sortList(next);
        ListNode dummy = new ListNode(), res = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                dummy.next = left;
                left = left.next;
            } else {
                dummy.next = right;
                right = right.next;
            }
            dummy = dummy.next;
        }
        dummy.next = left == null ? right : left;
        return res.next;
    }

    /**
     * p23 合并k个升序列表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> deque = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                deque.offer(node);
            }
        }
        ListNode dummy = new ListNode(), cur = dummy;
        while (!deque.isEmpty()) {
            ListNode node = deque.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                deque.offer(node.next);
            }
        }
        return dummy.next;
    }

    /**
     * p94 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return inorderRes;
    }

    List<Integer> inorderRes = new ArrayList<>();

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        inorderRes.add(node.val);
        dfs(node.right);
    }

    /**
     * p104 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * p226 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTree(root.left), right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * p101 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return check(left.left, right.right) && check(left.right, right.left);
    }

    /**
     * p543 二叉树的直径
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        countDepth(root);
        return diameter;
    }

    int diameter = 0;

    public int countDepth(TreeNode node) {
        if (node == null) return 0;
        int leftD = countDepth(node.left), rightD = countDepth(node.right);
        diameter = Math.max(diameter, leftD + rightD);
        return Math.max(leftD, rightD) + 1;
    }

    /**
     * p102 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                layer.add(cur.val);
            }
            res.add(layer);
        }
        return res;
    }

    /**
     * p108 将有序数组转换为二叉搜索树
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return constructTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructTree(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructTree(nums, l, mid - 1);
        root.right = constructTree(nums, mid + 1, r);
        return root;
    }

    /**
     * p98 验证二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, long bottom, long upper) {
        if (root == null) return true;
        return root.val > bottom && root.val < upper && isValid(root.left, bottom, root.val) && isValid(root.right, root.val, upper);
    }

    /**
     * p230 二叉搜索树中第k小的元素
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.pop();
            k--;
            if (k == 0) return cur.val;
            root = cur.right;
        }
        return -1;
    }

    /**
     * p199 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) res.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return res;
    }

    /**
     * p114 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left); // 左子树展开
        TreeNode right = root.right;
        root.right = root.left; // 右子树设为展开后的左子树
        root.left = null; // 左子树置空
        // 找到链表尾节点
        while (root.right != null) {
            root = root.right;
        }
        flatten(right); // 原右子树展开
        root.right = right; // 尾节点链接上展开后的右子树
    }

    /**
     * p105 从前序和中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            val2Idx.put(inorder[i], i);
        }
        return buildTree(preorder, 0, 0, preorder.length - 1);
    }

    Map<Integer, Integer> val2Idx = new HashMap<>();

    private TreeNode buildTree(int[] preorder, int root, int left, int right) {
        if (left > right) return null;
        TreeNode node = new TreeNode(preorder[root]);
        int inRootIdx = val2Idx.get(preorder[root]);
        node.left = buildTree(preorder, root + 1, left, inRootIdx - 1);
        node.right = buildTree(preorder, root + inRootIdx - left + 1, inRootIdx + 1, right);
        return node;
    }

}
