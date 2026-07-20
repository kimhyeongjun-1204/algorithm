import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : lighthouse) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[][] dp = new int[n + 1][2];   // [0]=끔, [1]=켬
        int[] parent = new int[n + 1];
        int[] order = new int[n];         // 방문 순서 기록
        boolean[] visited = new boolean[n + 1];

        // 반복 DFS로 방문 순서 확보 (재귀 대신 → 스택오버플로 방지)
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        visited[1] = true;
        int idx = 0;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            order[idx++] = cur;
            for (int next : adj.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = cur;
                    stack.push(next);
                }
            }
        }

        // 역순(잎 → 루트)으로 DP 계산
        for (int i = n - 1; i >= 0; i--) {
            int cur = order[i];
            dp[cur][1] = 1; // 자기 자신을 켜는 비용
            for (int child : adj.get(cur)) {
                if (child == parent[cur]) continue;
                dp[cur][0] += dp[child][1];
                dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }

        return Math.min(dp[1][0], dp[1][1]);
    }
}