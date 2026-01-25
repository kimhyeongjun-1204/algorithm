import java.lang.*; 
import java.util.*; 


class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int r = triangle.length; 
        int c = triangle[r-1].length; // 마지막 행의 열 개수  
        
        for(int i=1;i<r;i++) {
            triangle[i][0] += triangle[i-1][0]; 
            triangle[i][triangle[i].length-1] += triangle[i-1][triangle[i].length-2]; 
            
            for(int j=1;j<triangle[i].length-1;j++) {
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]); 
            }
        }
        
        return Arrays.stream(triangle[r-1]).max().getAsInt();
        
    }
}