/**
@author Javen
@create 2021-08-09
@Description 
*/
import org.junit.Test;

public class p264 {

    @Test
    public void test(){
        int i = nthUglyNumber(10);
        System.out.println(i);
    }

    public int nthUglyNumber(int n) {
        if (n<=6) return n;
        int[] dp = new int[n];
        int[] dp2 = new int[n];
        int[] dp3 = new int[n];
        int[] dp5 = new int[n];
        for (int i=0;i<6;i++){
            dp[i] = i+1;
            dp2[i] = (i+1)*2;
            dp3[i] = (i+1)*3;
            dp5[i] = (i+1)*5;
        }
        for (int i=6;i<n;i++){
            int target = dp[i-1];
            dp[i] = Math.min(binarySearch(dp2,i-1,target),Math.min(binarySearch(dp3,i-1,target),binarySearch(dp5,i-1,target)));
            dp2[i] = 2*dp[i];
            dp3[i] = 3*dp[i];
            dp5[i] = 5*dp[i];
        }
        return dp[n-1];
    }

    int binarySearch(int[] nums,int end,int target){
        int low = 0,high = end;
        int medium;
        while (low < high){
            medium = low + (high - low) / 2;
            if (nums[medium]<=target) low = medium+1;
            else high = medium;
        }
        return nums[low];
    }
}
