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

    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 6, 0, 1, 5, 4};
//        quickSort(arr); // 快速排序
//        bubbleSort(arr); // 冒泡排序
//        selectSort(arr); // 选择排序
//        insertSort(arr); // 插入排序
//        mergeSort(arr); // 归并排序
        heapSort(arr); // 堆排序
        System.out.println(Arrays.toString(arr));
    }

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

    // 归并排序
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    // 堆排序，最佳和平均时间复杂度均为O(nlogn)，不稳定
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, n);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
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

    protected static void mergeSort(int[] arr, int l, int r, int[] temp) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(arr, l, m, temp);
        mergeSort(arr, m + 1, r, temp);
        merge(arr, l, m, r, temp); // 归并
    }

    protected static void merge(int[] arr, int l, int m, int r, int[] temp) {
        for (int i = l; i <= r; i++) {
            temp[i] = arr[i];
        }
        int i = l, j = m + 1; // 双指针
        for (int k = l; k <= r; k++) {
            if (i == m + 1) arr[k] = temp[j++];
            else if (j == r + 1 || temp[j] >= temp[i]) arr[k] = temp[i++];
            else arr[k] = temp[j++];
        }
    }

    protected static void adjustHeap(int[] arr, int i, int len) {
        int parent = i, son = i * 2 + 1;
        while (son < len) {
            if (son + 1 < len && arr[son] < arr[son + 1]) son++;
            if (arr[son] > arr[parent]) {
                swap(arr, parent, son);
                parent = son;
                son = parent * 2 + 1;
            } else break;
        }
    }

    protected static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
