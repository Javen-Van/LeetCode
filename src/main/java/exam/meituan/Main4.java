package exam.meituan;

/**
 * @author Javen
 * @create 2022-03-12
 * @Description 小美现在打音游。这个音游的玩法是这样的：
 * —— 共有n个房间。小美初始拥有一个指针，指在一号房间。
 * —— 游戏共持续m秒，每秒会有一个房间产生炸弹，小美的指针不能在这个房间中。
 * —— 每秒结束的瞬间，小美可以使用一次魔法，把指针切换到另一个房间中，该过程会消耗一个能量。
 * 你的任务是计算小美无伤通过音游所需要消耗的最小能量。
 * 保证第一秒的炸弹不发生在一号房间中。
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), m = sc.nextInt();
            int[] bomb = new int[m];
            for (int i = 0; i < m; i++) {
                bomb[i] = sc.nextInt();
            }
            System.out.println(method(bomb, n));
        }
    }

    public static int method(int[] bomb, int n) {
        int res = 0, m = bomb.length, room = 0;
        Deque<Integer>[] list = new Deque[n];
        for (int i = 0; i < n; i++) {
            list[i] = new LinkedList<>();
        }
        for (int i = 0; i < m; i++) {
            list[bomb[i] - 1].offerLast(i);
        }
        for (int k : bomb) {
            list[k - 1].pollFirst();
            if (room == k - 1) {
                int max = 0, idx = 0;
                for (int j = 0; j < n; j++) {
                    if (j == room) continue;
                    if (list[j].isEmpty()) {
                        idx = j;
                        break;
                    }
                    if (list[j].peekFirst() > max) {
                        max = list[j].peekFirst();
                        idx = j;
                    }
                }
                room = idx;
                res++;
            }
        }
        return res;
    }

}