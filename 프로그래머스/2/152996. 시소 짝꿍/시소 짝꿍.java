import java.util.*; 
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        HashMap<Integer, Integer> comb = new HashMap<>(); 
        HashMap<Integer, Integer> countMap = new HashMap<>(); // 같은 무게 개수
        
        Arrays.sort(weights); 
        
        for(int w : weights) {
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
            
            for(int i = 2; i <= 4; i++) {
                int weight = w * i; 
                comb.put(weight, comb.getOrDefault(weight, 0) + 1); 
            }
        }
        
        for(int weight : comb.keySet()) {
            int value = comb.get(weight); 
            if(value <= 1) continue; 
            answer += (long) value * (value - 1) / 2;
        }
        
        // 같은 무게끼리는 3가지 배수(x2,x3,x4)에서 각각 잡혀서
        // 3번 카운트됨. 실제로는 1번만 세야 하므로 2번분 제거
        for(int count : countMap.values()) {
            if(count >= 2) {
                long pairs = (long) count * (count - 1) / 2;
                answer -= pairs * 2;  // 3번 잡힌 것을 1번으로 (3-1=2 제거)
            }
        }
        
        return answer;
    }
}