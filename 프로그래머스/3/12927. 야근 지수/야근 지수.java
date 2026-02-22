import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 1. 최대 힙(가장 큰 수가 먼저 나옴) 구성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }

        // 2. n 시간 동안 가장 큰 작업량을 1씩 줄임
        for (int i = 0; i < n; i++) {
            int max = pq.poll();
            if (max <= 0) return 0; // 더 이상 줄일 작업이 없으면 0 반환
            pq.offer(max - 1);
        }

        // 3. 남은 작업량의 제곱의 합 계산
        long answer = 0;
        while (!pq.isEmpty()) {
            long val = pq.poll();
            answer += val * val;
        }

        return answer;
    }
}