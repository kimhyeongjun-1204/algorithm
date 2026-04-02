import java.lang.*; 

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        
        long right = (long) 4e14, left = 1, mid = 0; 
        
        while(left <= right) {
            mid = (left + right) / 2; 
            
            long gTotal = 0 , sTotal = 0;
            long total = 0; 
            
            for(int i=0;i<g.length;i++) {
                long go = mid / t[i];  // 1 : 1  , 2 : 1 , 3 : 2 4 : 2
                long goes = (go <=0) ? 0 : (go-1) / 2 + 1; 
                
                gTotal += Math.min(g[i], goes * w[i]); 
                sTotal += Math.min(s[i], goes * w[i]);
                total += Math.min(g[i]+s[i] ,goes * w[i]);
            }
            
            if(gTotal >= a && sTotal >= b && total >= a+b) {
                right = mid-1; 
            }else {
                left = mid+1; 
            }
            
        }
        
        
        
        
        return left;
    }
}