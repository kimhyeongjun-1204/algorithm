import java.util.*;

class Solution {
    public int[] solution(int target) {
        // dp[i] = {최소 다트 수, 그때 최대 싱글/불 횟수}
        int[][] dp = new int[target + 1][2];
        
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE; // 다트 수
            dp[i][1] = 0;                // 싱글/불 횟수
        }
        
        // 싱글/불 점수 (카운트 +1)
        ArrayList<Integer> singles = new ArrayList<>();
        for (int i = 1; i <= 20; i++) singles.add(i);
        singles.add(50);
        
        // 더블/트리플 점수 (카운트 +0), 싱글과 겹치지 않는 것만
        ArrayList<Integer> multis = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            int d = i * 2;
            int t = i * 3;
            if (d > 20 && d != 50) multis.add(d);
            if (t > 20 && t != 50) multis.add(t);
        }
        
        for (int i = 1; i <= target; i++) {
            // 싱글/불로 득점
            for (int s : singles) {
                if (s > i) continue;
                int prev = i - s;
                int darts = dp[prev][0] + 1;
                int sb = dp[prev][1] + 1;
                if (darts < dp[i][0] || (darts == dp[i][0] && sb > dp[i][1])) {
                    dp[i][0] = darts;
                    dp[i][1] = sb;
                }
            }
            // 더블/트리플로 득점
            for (int m : multis) {
                if (m > i) continue;
                int prev = i - m;
                int darts = dp[prev][0] + 1;
                int sb = dp[prev][1];
                if (darts < dp[i][0] || (darts == dp[i][0] && sb > dp[i][1])) {
                    dp[i][0] = darts;
                    dp[i][1] = sb;
                }
            }
        }
        
        return dp[target];
    }
}