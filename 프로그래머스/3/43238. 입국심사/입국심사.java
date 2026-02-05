import java.util.*; 

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long right = (long)Arrays.stream(times).max().getAsInt() * n ,
            left = 1,
            mid; 

        long process; 
        
        while(left <= right) {
            process = 0; 
            mid = (right + left) / 2; 
            System.out.println(left + " " + mid + " " + right); 
            // mid Time 안으로 처리할 수 있는지 
            for(int t : times) {
                process += mid / t; 
                if(process >= n) break; 
            }
            if(process >= n) {
                answer = mid; 
                right = mid - 1; 
            }else {
                left = mid+1; 
            }
        }
        
        return answer; 
    }
}