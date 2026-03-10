import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        // 인접 리스트 구성
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for (int[] r : road) {
            graph.get(r[0]).add(new int[]{r[1], r[2]});
            graph.get(r[1]).add(new int[]{r[0], r[2]});
        }
        
        // 다익스트라
        int[] d = new int[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{1, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], cost = cur[1];
            
            if (cost > d[node]) continue;
            
            for (int[] next : graph.get(node)) {
                int newCost = cost + next[1];
                if (newCost < d[next[0]]) {
                    d[next[0]] = newCost;
                    pq.offer(new int[]{next[0], newCost});
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (d[i] <= K) answer++;
        }
        return answer;
    }
}