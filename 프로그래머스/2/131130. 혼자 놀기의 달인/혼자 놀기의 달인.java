import java.util.*; 

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        // 그룹 정보 
        int[] group = new int[cards.length]; 
        
        int gNum = 1; // 1번 그룹부터 시작 
        for(int i=0;i<cards.length;i++) {
            if(group[i] == 0) {
                grouping(gNum, i, group, cards); 
                gNum++; 
            }
        }
        
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); 
        for(int i=1;i<gNum;i++) {
            int size = 0;
            for(int j=0;j<group.length;j++) {
                if(group[j] == i) size++; 
            }
            max.add(size); 
        }
        
        if(max.size() == 1) return 0; 
        return max.poll() * max.poll();
    }
    
    void grouping(int gNum , int idx, int[] group, int[] cards) {
        if(group[idx] == gNum) return;
        group[idx] = gNum; 
        grouping(gNum, --cards[idx], group, cards); 
    }
}