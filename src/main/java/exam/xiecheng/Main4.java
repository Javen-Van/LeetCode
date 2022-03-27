package exam.xiecheng;


import java.io.*;
import java.util.*;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = reader.readLine().split(" ");
        int m = Integer.parseInt(s[0]), n = Integer.parseInt(s[1]), q = Integer.parseInt(s[2]);
        char[][] matrix = new char[m][];
        for (int i = 0; i < m; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }
        int[][] changes = new int[q][4];
        for (int i = 0; i < q; i++) {
            String[] nums = reader.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                changes[i][j] = Integer.parseInt(nums[j]) - 1;
            }
            for (int k = changes[i][0]; k <= changes[i][2]; k++) {
                for (int l = changes[i][1]; l <= changes[i][3]; l++) {
                    char c = matrix[k][l];
                    matrix[k][l] = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
                }
            }
        }
        for (char[] re : matrix) {
            writer.write(re);
            writer.newLine();
        }
        writer.flush();
    }

    public static char[][] method(char[][] matrix, int[][] changes) {
        for (int[] nums : changes) {
            for (int i = nums[0]; i <= nums[2]; i++) {
                for (int j = nums[1]; j <= nums[3]; j++) {
                    char c = matrix[i][j];
                    matrix[i][j] = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
                }
            }
        }
        return matrix;
    }
}
