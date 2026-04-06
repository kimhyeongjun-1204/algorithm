import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        // win[i][j] = true면 i가 j를 이김
        boolean[][] win = new boolean[n + 1][n + 1];
        
        for (int[] r : results) {
            win[r[0]][r[1]] = true;
        }
        
        // 플로이드-워셜: i가 k를 이기고 k가 j를 이기면 → i가 j를 이김
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (win[i][k] && win[k][j]) {
                        win[i][j] = true;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                // i와 j 사이의 승패를 알 수 있는 경우
                if (win[i][j] || win[j][i]) count++;
            }
            if (count == n - 1) answer++;
        }
        
        return answer;
    }
}