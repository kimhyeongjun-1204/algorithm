import java.lang.Math;
import java.util.*; 

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int max_alp = 0; 
        int max_cop = 0; 
        
        // alp : 알고력 , cop : 코딩력 
        
        for(int[] problem : problems) {
            if(problem[0] > max_alp) {
                max_alp = problem[0];                 
            }
            
            if(problem[1] > max_cop) {
                max_cop = problem[1]; 
            }
        }
        
        alp = Math.min(alp, max_alp); 
        cop = Math.min(cop, max_cop); 
        
        int[][] dp = new int[max_alp+1][max_cop+1]; 
        for(int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE); 
        } 
        dp[alp][cop] = 0; 
        
        for(int i=alp;i<=max_alp;i++) {
            for(int j=cop;j<=max_cop;j++) {
                if(i < max_alp) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1); 
                }
                
                if(j < max_cop) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1); 
                }
                
                for(int[] problem : problems) {
                    if(i >= problem[0] && j >= problem[1]) {
                        int nAlp = Math.min(max_alp, i + problem[2]), 
                            nCop = Math.min(max_cop, j + problem[3]); 
                        
                        dp[nAlp][nCop] = Math.min(dp[nAlp][nCop] ,dp[i][j] + problem[4]);
                    } 
                }
                
                
            }
        }        
        
        return dp[max_alp][max_cop];
    }
}