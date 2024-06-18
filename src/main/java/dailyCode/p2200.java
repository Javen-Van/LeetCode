package dailyCode;

public class p2200 {

    /**
     * 价格减免
     *
     * @param sentence
     * @param discount
     * @return
     */
    public String discountPrices(String sentence, int discount) {
        String[] sent = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : sent) {
            if (s.charAt(0) == '$' && s.length() > 1) {
                boolean isNumber = true;
                for (int i = 1; i < s.length(); i++) {
                    if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                        isNumber = false;
                        break;
                    }
                }
                if (isNumber) {
                    long num = Long.parseLong(s.substring(1));
                    double afterDis = num / 100.0 * (100 - discount);
                    sb.append('$');
                    sb.append(String.format("%.2f", afterDis));
                    sb.append(' ');
                    continue;
                }
            }
            sb.append(s).append(' ');
        }
        return sb.substring(0, sb.length() - 1);
    }

}
