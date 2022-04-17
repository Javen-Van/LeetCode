package bean;

import java.util.*;

/**
 * @author Javen
 * @create 2022-04-13
 * @Description p380 O(1)时间插入、删除、随机获取元素
 */

class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        map.put(list.get(list.size() - 1), index); // 交换待删除元素与末尾元素
        Collections.swap(list, index, list.size() - 1);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
