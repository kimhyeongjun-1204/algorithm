import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        int len = a.length;

        long sum = 0;
        for (int num : a) sum += num;
        if (sum != 0) return -1;

        // 트리 구성
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < len; i++) tree.add(new ArrayList<>());
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // 0번을 루트로, BFS로 부모-자식 관계 & 후위순회 순서 구하기
        int[] parent = new int[len];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[len];
        List<Integer> order = new ArrayList<>(); // BFS 순서 (나중에 역순 = 후위순회)

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order.add(cur);
            for (int next : tree.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = cur;
                    queue.add(next);
                }
            }
        }

        // 리프부터 루트 방향으로 (BFS 역순) 서브트리 합을 올리기
        long[] weight = new long[len];
        for (int i = 0; i < len; i++) weight[i] = a[i];

        long answer = 0;
        for (int i = order.size() - 1; i >= 1; i--) {
            int node = order.get(i);
            // 이 노드 → 부모 간선을 통과하는 양 = |weight[node]|
            answer += Math.abs(weight[node]);
            // 부모에게 값을 넘김
            weight[parent[node]] += weight[node];
        }

        return answer;
    }
}