class Solution {
    public int[] boxes(int w , int box) {
        int layer = 0; // 층수 
        int index; // 몇번째 열인지 
        
        while(true) {
            if(w * layer + 1 <= box && box <= w * (layer+1)) {
                layer++; 
                index = (box % w == 0) ? w : box % w;
                if(layer % 2 == 0) {
                    index = w - index + 1; 
                }
                break; 
            }
            
            layer++; 
        }
        return new int[]{layer, index};
    } 
    
    public int solution(int n, int w, int num) {
        int answer = 0;
        
//      1. n과 num의 층수와 인덱스 찾기
        int[] n_loc = boxes(w, n); 
        int[] num_loc = boxes(w,num); 
        
        answer = n_loc[0] - num_loc[0]; 
        if(n_loc[0] % 2 == 0) {
            if(n_loc[1] <= num_loc[1]) {
                answer++; 
            }
        }else {
            if(n_loc[1] >= num_loc[1]) {
                answer++; 
            }
        }
        
        
        return answer;
    }
}