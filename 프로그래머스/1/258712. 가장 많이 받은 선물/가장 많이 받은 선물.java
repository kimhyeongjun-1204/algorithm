import java.util.*; 
import java.lang.Math;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // 1. 인덱스 설정 
        HashMap<String , Integer> index = new HashMap<>(); 
        for(int i=0;i<friends.length;i++) {
            index.put(friends[i] , i); 
        }
        
        int[][] gift = new int[50][50]; 
        int[][] rate = new int[50][2]; // 준선물 , 받은 선물 
        
        for(String g : gifts) {
            String give = g.split(" ")[0],
                   take = g.split(" ")[1]; 
            
            int giveI = index.get(give),
                takeI = index.get(take); 
            
            gift[giveI][takeI]++;
            rate[giveI][0]++;
            rate[takeI][1]++; 
        }
        
        // for(int i=0;i<friends.length;i++) {
        //     System.out.println(rate[i][0] + " " + rate[i][1]);
        // }
        
        int[] total = new int[50]; 
        for(int i=0;i<friends.length;i++) {
            for(int j=i+1;j<friends.length;j++) {
                if(gift[i][j] > gift[j][i]) {
                    total[i]++;
                }else if(gift[i][j] == gift[j][i]) {
                    int iRate = rate[i][0] - rate[i][1],
                        jRate = rate[j][0] - rate[j][1]; 
                    
                    if(iRate > jRate) total[i]++; 
                    else if(iRate < jRate) total[j]++; 
                }else {
                    total[j]++; 
                }
            }
        }
        
        for(int i=0;i<friends.length;i++) {
            answer = Math.max(answer, total[i]); 
        }
        
        return answer;
    }
}