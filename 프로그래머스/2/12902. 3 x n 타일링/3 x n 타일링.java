class Solution {
    public int solution(int n) {
        final int MOD = 1_000_000_007;
        int m = n / 2;                 // n은 짝수일 때만 답이 존재
        long[] dp = new long[m + 1];
        dp[0] = 1;
        if (m >= 1) dp[1] = 3;
        for (int i = 2; i <= m; i++)
            dp[i] = (4 * dp[i - 1] - dp[i - 2] + MOD) % MOD;
        return (int) dp[m];
    }
}