import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            for (int i = 0; i < s.length(); i += 8) {
                StringBuilder sb = new StringBuilder(s.substring(i, i + 8));
                if (i + 8 > s.length()) {
                    for (int j = s.length(); j < i + 8; j++) {
                        sb.append("0");
                    }
                }
                System.out.println(sb);
            }
        }
    }
}
