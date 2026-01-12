import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // 1. 선수 이름과 인덱스(등수)를 매핑할 HashMap 생성
        Map<String, Integer> rankMap = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            rankMap.put(players[i], i);
        }
        
        // 2. 경주 진행
        for (String caller : callings) {
            // 불린 선수의 현재 등수 찾기 (O(1))
            int currentRank = rankMap.get(caller);
            
            // 추월할 앞 선수의 정보 파악
            String frontPlayer = players[currentRank - 1];
            
            // 3. 배열(players)에서 위치 스왑
            players[currentRank] = frontPlayer;
            players[currentRank - 1] = caller;
            
            // 4. Map(rankMap)에서 등수 정보 업데이트
            rankMap.put(caller, currentRank - 1);
            rankMap.put(frontPlayer, currentRank);
        }
        
        return players;
    }
}