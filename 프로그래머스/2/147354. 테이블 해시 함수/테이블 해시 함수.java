import java.util.*; 

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        
        Arrays.sort(data, (a,b) -> a[col-1] != b[col-1] ? a[col-1] - b[col-1] : b[0] - a[0]);         
        
        int[] S_i = new int[row_end - row_begin +1]; 
        
        for(int i=row_begin;i<=row_end;i++) {
            for(int j=0;j<data[i-1].length;j++) {
                S_i[i-row_begin] +=  data[i-1][j]% i;     
            }
        }
        
        
        for(int n : S_i) answer ^= n; 
        
        return answer;
    }
}