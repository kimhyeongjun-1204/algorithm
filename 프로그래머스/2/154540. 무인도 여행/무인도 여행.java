import java.util.*; 

class Solution {
    ArrayList<Integer> answer = new ArrayList<>();
    int R;
    int C;
    
    public ArrayList<Integer> solution(String[] maps) {
        R = maps.length; 
        C = maps[0].length();
        
        int[][] map = new int[R][C]; 
        boolean[][] visited = new boolean[R][C]; 
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                char ch = maps[i].charAt(j); 
                if(ch == 'X') {
                    map[i][j] = 0; 
                }else {
                    map[i][j] = ch - '0'; 
                }
            }
        }
        
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true; 
                    answer.add(dfs(i , j, map, visited)); 
                }
            }
        }
        
        Collections.sort(answer); 
        if(answer.isEmpty()) answer.add(-1); 
        return answer;
    }
    
    int dfs(int i, int j, int[][] map, boolean[][] visited) {
        int[][] move = {{1,0}, {0,1} , {-1,0}, {0,-1}}; 
        int sum = map[i][j]; 
        
        for(int k=0;k<4;k++) {
            int nI = i + move[k][0];
            int nJ = j + move[k][1]; 
            
            if(nI >= 0 && nI < R && nJ >=0 && nJ < C) {
                if(map[nI][nJ] != 0 && !visited[nI][nJ]) {
                    visited[nI][nJ] = true;
                    sum += dfs(nI, nJ, map, visited); 
                }
            }
        }
        
        return sum; 
    }
}