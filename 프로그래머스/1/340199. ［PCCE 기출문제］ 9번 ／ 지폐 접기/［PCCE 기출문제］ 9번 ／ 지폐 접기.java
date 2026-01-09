class Solution {
    
    public int solution(int[] wallet, int[] bill) {
        // 1. 지갑 크기 : wallet , bill : 지폐의 가로세로 크기
        int answer = 0;
        
        int w_long = wallet[0], w_short = wallet[1]; 
        if(wallet[0] < wallet[1]) {
            w_long = wallet[1]; 
            w_short = wallet[0]; 
        }

        int b_long = bill[1], b_short = bill[0]; 
        
        while(true) {
            if(b_long < b_short) {
                int p = b_long; 
                b_long = b_short;
                b_short = p;  
            }
            
            if(w_long >= b_long && w_short >= b_short) { 
                break; 
            }
            
            b_long /= 2; 
            answer++; 
        }    
            
        return answer;
    }
}