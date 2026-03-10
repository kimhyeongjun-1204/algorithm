import java.util.*; 

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        List<int[]> bridge = new ArrayList<>(); 
        
        int time = 0; 
        int trucks = 0; // 들어가야 하는 트럭번호 
        
        while(true) {
            time++; 
            
            // 트럭 한칸씩 이동
            for(int[] arr : bridge) {
                arr[1]++; 
            }
           
            // 다리를 지난 트럭을 없앰 
            if(!bridge.isEmpty() && bridge.get(bridge.size()-1)[1] > bridge_length) bridge.remove(bridge.size()-1); 
            
            // 트럭 + 1             
            if(bridge.size() < bridge_length) {
                if(trucks < truck_weights.length && total_weight(bridge) + truck_weights[trucks] <= weight) {
                    bridge.add(0, new int[]{truck_weights[trucks++] , 1}); 
                }    
            }
            

            
            if(bridge.isEmpty() && trucks >= truck_weights.length) break; 
        }
        
        return time;
    }
    
    int total_weight(List<int[]> list) {
        int sum = 0; 
        
        for(int[] li : list) {
            sum += li[0]; 
        }
        
        return sum; 
    }
}