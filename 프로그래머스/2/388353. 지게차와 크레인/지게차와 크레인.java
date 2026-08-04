import java.util.*; 

class Solution {
    int R, C; 
    int[][] move = {{0,1}, {1,0}, {0,-1}, {-1,0}}; 
    
    public int solution(String[] storage, String[] requests) {
        R = storage.length; C = storage[0].length(); 
        int answer = R*C;
        
        boolean[][] out = new boolean[R][C]; 
        
        for(String request : requests) {
            ArrayList<int[]> list = new ArrayList<>(); 
                
            boolean allOut = false; 
            char ch = request.charAt(0); 
            if(request.length() > 1) allOut = true; 
            
            for(int r=0;r<R;r++) {
                for(int c=0;c<C;c++) {
                    if(out[r][c]) continue; 
                    
                    if(storage[r].charAt(c) == ch) {
                        if(allOut) {
                            list.add(new int[]{r,c});
                            answer--; 
                        }else {
                            Queue<int[]> queue = new ArrayDeque<>(); 
                            queue.offer(new int[]{r, c}); 
                            boolean[][] visited = new boolean[R][C]; 
                            // System.out.println("r,c : " + r + ", " + c); 
                            
                            while(!queue.isEmpty()) {
                                int[] now = queue.poll(); 
                                int nR = now[0], nC = now[1]; 
                                // System.out.println("r,c : " + nR + ", " + nC); 
                                visited[nR][nC] = true; 
                                
                                if(isOut(nR, nC)) {
                                    list.add(new int[]{r,c});
                                    answer--;  
                                    break; 
                                } 
                                
                                for(int[] m : move) {
                                    int neR = nR + m[0], neC = nC + m[1]; 
                                    
                                    if(neR < 0 || neC < 0 || neR >= R || neC >= C)
                                        continue; 
                                    
                                    if(!visited[neR][neC] && out[neR][neC]) 
                                        queue.offer(new int[]{neR, neC}); 
                                }
                            }
                        }
                    }
                }
            }
            
            for(int[] li : list) out[li[0]][li[1]] = true; 
        }
        
        return answer;
    }
    
     
    
    boolean isOut(int r, int c) {
        if(r == 0 || c == 0 || r == (R-1) || c == (C-1) )
            return true; 
        
        return false; 
    }
}