package exam.ali;

/**
 * @author Javen
 * @create 2022-03-14
 * @Description
 */

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String s = in.next();
            System.out.println(count(s));
        }
    }

    public static long count(String s) {
        int n = s.length();
        if (n < 3) return 0;
        String str = s.substring(2);
        long count = 0L;
        int[] help = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                count += help[c - 'a' + 10];
            } else {
                count += help[c - '0'];
            }
        }
        return count;
    }

    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt(), m = in.nextInt();
            int[][] grid = new int[n][m];
            int[] countX = new int[n], countY = new int[m];
            int[] countX0 = new int[n], countY0 = new int[m];
            long res = 0L;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = in.nextInt();
                    if (grid[i][j] == 0) {
                        if (countX[i] > 0) res++;
                        if (countY[j] > 0) res++;
                        countX0[i]++;
                        countY0[j]++;
                    } else {
                        countX[i]++;
                        countY[j]++;
                        if (countX0[i] > 0) {
                            res += countX0[i];
                            countX0[i]=0;
                        }
                        if (countY0[j]>0){
                            res += countY0[j];
                            countY0[j] = 0;
                        }
                    }
                }
            }
            System.out.println(res);
        }
    }

    public static long method2(int[][] grid, int n, int m) {
        int[] countX = new int[n], countY = new int[m];
        long res = 0L;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    if (countX[i] > 0) res++;
                    if (countY[j] > 0) res++;
                }
                countX[i] += grid[i][j];
                countY[j] += grid[i][j];
            }
        }
        return res;
    }

}