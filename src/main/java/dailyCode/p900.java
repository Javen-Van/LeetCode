package dailyCode;

public class p900 {

    // p917 仅仅反转字母｛双指针｝
    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < arr.length && !Character.isLetter(arr[i])) i++;
            while (j >= 0 && !Character.isLetter(arr[j])) j--;
            if (i == arr.length || j == -1) break;
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }
}
