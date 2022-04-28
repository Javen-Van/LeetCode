package exam.zhaoshang;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] a = new int[n], b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt() - 1;
            }
            for (int i = 0; i < n; i++) {
                b[sc.nextInt() - 1] = i;
            }
            int res = 0;
            Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int i = 0; i < n; i++) {
                if (!queue.isEmpty() && b[a[i]] < queue.peek()) res++;
                queue.offer(b[a[i]]);
            }
            System.out.println(res);
        }
    }
}
