package dailyCode;

import org.junit.Test;

public class p2800 {

    /**
     * p2806 取整购买后的账户余额
     * @param purchaseAmount
     * @return
     */
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int div = purchaseAmount / 10;
        return 100 - 10 * (div + (purchaseAmount % 10 < 5 ? 0 : 1));
    }

}
