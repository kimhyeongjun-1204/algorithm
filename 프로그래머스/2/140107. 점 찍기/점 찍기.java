import java.lang.Math; 

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        // a^2 + b^2 <= d^2 / k^2 
        
        double max = Math.pow(d,2) / Math.pow(k,2); 
        double num = d / k; 
        
        while(num >= 0) {
            long root = (long)Math.sqrt(max - Math.pow(num,2)); 
            
            root = Math.max(0, root); 
            answer += (root+1); 
            num--;
        }
            
            
        return answer;
    }
}