class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        int MOD = 1_000_000_007;

        // 0원을 만드는 방법은 1가지 (아무 동전도 선택하지 않음)
        dp[0] = 1;

        for (int coin : money) {
            for (int i = coin; i <= n; i++) {
                // 현재 금액 i에서 coin을 뺀 금액의 경우의 수를 더함
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }

        return dp[n];
    }
}