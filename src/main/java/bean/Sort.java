package bean;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2022-01-10
 * @Description
 */
public class Sort {

    // 快速排序
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // 冒泡排序，完全有序的情况下，最佳时间复杂度O(n)，平均时间复杂度O(n2)，稳定
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) break; // 如果本轮没有任何交换，则排序完成，直接跳出
        }
    }

    public static void mergeSort(int[] arr) {

    }

    // 选择排序，时间复杂度均为O(n2)，不稳定
    public static void selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            swap(arr, min, i);
        }
    }

    // 插入排序，时间复杂度均为O(n2)，稳定的
    public static void insertSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i], j = i - 1;
            while (j > 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    protected static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int index = partition(arr, l, r);
        quickSort(arr, l, index - 1);
        quickSort(arr, index + 1, r);
    }

    protected static int partition(int[] arr, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        return i;
    }

    protected static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
