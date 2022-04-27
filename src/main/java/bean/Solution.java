package bean;

import java.util.*;

/**
 * @author Javen
 * @create 2022-04-26
 * @Description p398 随机数索引
 */

public class Solution {

    Map<Integer, List<Integer>> map = new HashMap<>();
    Random rn = new Random();

    public Solution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(rn.nextInt(list.size()));
    }
}
