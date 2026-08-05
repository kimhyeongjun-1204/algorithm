class Solution {
    long limit;
    int[] diffs, times; 
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int le = 1, ri = 0; 
        this.limit = limit; 
        this.diffs = diffs; 
        this.times = times; 
        
        if(diffs.length == 1) return diffs[0]; 
        for(int diff : diffs) ri = Math.max(ri, diff); 
            
        while(le <= ri) {
            int mid = le + (ri - le) / 2;    
            
            if(underLimit(mid)) {
                answer = mid;
                ri = mid-1; 
            }else {
                le = mid+1; 
            }
        }
        
        return answer;
    }
    
    boolean underLimit(int level) {
        int len = diffs.length; 
        long sum = times[0]; 
        int time_prev = times[0];  
        
        for(int i=1;i<len;i++) {
            int diff = diffs[i]; 
            int time_cur = times[i];     
            int wrong = diff - level; 
            
            if(wrong > 0) {
                int total = (time_cur + time_prev) * wrong + time_cur; 
                sum += total; 
            }else {
                sum += time_cur; 
            }
            
            time_prev = time_cur; 
        }
        
        if(limit >= sum) return true; 
        return false; 
    
    }
}