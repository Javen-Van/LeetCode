package swordToOffer;

/**
 * @author Javen
 * @create 2022-01-07
 * @Description 数值的整数次方
 */
public class offer16 {

    public double myPow(double x, int n) {
        if (x == 0.0) return 0;
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        if ((n & 1) == 1) return x * myPow(x, n - 1);
        return myPow(x * x, n / 2);
    }

}
