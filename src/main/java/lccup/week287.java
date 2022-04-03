package lccup;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-04-03
 * @Description
 */
public class week287 {

    // Q1
    public int convertTime(String current, String correct) {
        String[] cur = current.split(":");
        String[] cor = correct.split(":");
        int curTime = Integer.parseInt(cur[0]) * 60 + Integer.parseInt(cur[1]);
        int corTime = Integer.parseInt(cor[0]) * 60 + Integer.parseInt(cor[1]);
        int dif = corTime - curTime, res = 0;
        res += dif / 60;
        dif %= 60;
        res += dif / 15;
        dif %= 15;
        res += dif / 5;
        dif %= 5;
        res += dif;
        return res;
    }

    // Q2
    public List<List<Integer>> findWinners(int[][] matches) {
        int[] count = new int[100001];
        Arrays.fill(count, -1);
        for (int[] match : matches) {
            int winner = match[0], loser = match[1];
            if (count[winner] == -1) count[winner] = 0;
            if (count[loser] == -1) count[loser] = 1;
            else count[loser]++;
        }
        List<Integer> zero = new ArrayList<>(), one = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 100001; i++) {
            if (count[i] == 0) zero.add(i);
            if (count[i] == 1) one.add(i);
        }
        res.add(zero);
        res.add(one);
        return res;
    }

    // Q3
    public int maximumCandies(int[] candies, long k) {
        int min = Integer.MAX_VALUE;
        long sum = 0L;
        for (int candy : candies) {
            min = Math.min(candy, min);
            sum += candy;
        }
        if (k > sum) return 0;
        if (min * k <= sum) return min;
        return 0;
    }

    // Q4
}

class Encrypter {

    Map<Character, String> map;
    Map<String, List<Character>> deMap;
    Trie trie;

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        map = new HashMap<>();
        deMap = new HashMap<>();
        trie = new Trie();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
            List<Character> list = deMap.getOrDefault(values[i], new ArrayList<>());
            list.add(keys[i]);
            deMap.put(values[i], list);
        }
        for (String s : dictionary) {
            trie.insert(s);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word1.length(); i++) {
            sb.append(map.get(word1.charAt(i)));
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return dfs(word2, trie);
    }

    public int dfs(String word, Trie root) {
        if ("".equals(word)) return root.isWord ? 1 : 0;
        List<Character> list = deMap.get(word.substring(0, 2));
        if (list == null) return 0;
        int res = 0;
        for (Character c : list) {
            Trie next = root.children[c - 'a'];
            if (next != null) res += dfs(word.substring(2), next);
        }
        return res;
    }

    public static class Trie {
        boolean isWord;
        Trie[] children;

        public Trie() {
            this.isWord = false;
            this.children = new Trie[26];
        }

        public void insert(String s) {
            Trie root = this;
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (root.children[index] == null) root.children[index] = new Trie();
                root = root.children[index];
            }
            root.isWord = true;
        }
    }
}
