import java.util.*; 
import java.lang.*; 

class Solution {
    int R, C; 
    int[][] target;
    int answer = Integer.MAX_VALUE;
    int cnt = 0;
    
    public int solution(int[][] beginning, int[][] target) {
        
        R = beginning.length; 
        C = beginning[0].length; 
        this.target = target; 
       
        int[][] copy = new int[R][C]; 
        for(int r=0;r<R;r++) {
            for(int c=0;c<C;c++) {
                copy[r][c] = beginning[r][c]; 
            }
        }
        
        rotate(false , copy);
        cnt = 0;
        rotate(true , beginning);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    void rotate(boolean rotateR,int[][] now) {
        ArrayList<Integer> cols = new ArrayList<>(); 
        if(rotateR) cnt++; 
        
        for(int c=0;c<C;c++) {
            boolean same = (now[0][c] == target[0][c]); 
            if(rotateR &&  same || !rotateR && !same) 
                cols.add(c); 
        }        
        rotateC(now, cols); 
        
        for(int r=1;r<R;r++) {
            boolean rotate = (now[r][0] != target[r][0]); 
            if(rotate) cnt++; 
            for(int c=1; c<C;c++) {
                boolean same = (now[r][c] == target[r][c]);
                if((rotate && same) || (!rotate && !same)) {
                    return; 
                }
            }
        }
        
        answer = Math.min(answer, cnt); 
    }    
    
    void rotateC(int[][] now, ArrayList<Integer> cols) {
        for(int c : cols) {
            for(int r=1;r<R;r++)
                now[r][c] = (now[r][c]+1) % 2; 
        }
        
        cnt += cols.size(); 
    }
}