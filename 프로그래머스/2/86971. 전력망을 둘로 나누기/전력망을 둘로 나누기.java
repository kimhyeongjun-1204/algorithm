import java.lang.Math; 
import java.util.*; 


class Solution {
    ArrayList<Integer>[] list; 
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        list = new ArrayList[n+1];
        for(int i=1;i<n+1;i++) {
            list[i] = new ArrayList<>(); 
        }
        
        for(int[] wire : wires) {
            int w1 = wire[0];
            int w2 = wire[1]; 
            
            list[w1].add(w2);                
            list[w2].add(w1);                
        }        
        
        for(int[] wire : wires) {
            int w1 = wire[0];
            int w2 = wire[1]; 
            
            list[w1].remove((Object)w2);                
            list[w2].remove((Object)w1);
             
            int len1 = bfs(w1, new boolean[n+1]); 
            int len2 = bfs(w2, new boolean[n+1]); 
            answer = Math.min(answer, Math.abs(len1 - len2)); 
            
            list[w1].add(w2);                
            list[w2].add(w1);
        }   
        
        return answer;
    }
    
//  bfs를 통한 개수 구하기 
    int bfs(int node, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>(); 
        queue.add(node); 
        int len = 0; 
        visited[node] = true; 
        
        while(!queue.isEmpty()) {
            int now = queue.poll(); 
            for(int next : list[now]) {
                if(!visited[next]) {
                    visited[next] = true; 
                    queue.add(next); 
                }
            }                
            
            len++; 
        }
        
        return len; 
    }
}