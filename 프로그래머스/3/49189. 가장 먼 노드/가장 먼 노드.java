import java.util.*; 
import java.lang.*; 


class Solution {
    ArrayList<Integer>[] list; 
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int[] distance = new int[n+1];
        Arrays.fill(distance, -1); 
        distance[0] = distance[1] = 0;
        
//      1. 간선 정보 
        list = new ArrayList[n+1];   
        for(int i=1;i<n+1;i++) {
            list[i] = new ArrayList<Integer>(); 
        }
        
        for(int[] e : edge) {
            list[e[0]].add(e[1]); 
            list[e[1]].add(e[0]); 
        }
        
        
        
//      bfs 진행 
        ArrayDeque<Integer> queue = new ArrayDeque<>(); 
        queue.add(1); 
        int max = -1; 
        
        while(!queue.isEmpty()) {
            int node = queue.poll(); 
            // System.out.println("현재 노드 : " + node);
            // System.out.print("다음 노드 : ");
            for(int next : list[node]) {
                if(distance[next] == -1) {
                    // System.out.print(next + "  "); 
                    queue.add(next); 
                    distance[next] = distance[node]+1; 
                    max = Math.max(max, distance[next]); 
                }
            }
            // System.out.println(); 
            // System.out.println(); 
        }
        
        // 최대 개수 찾기 
        for(int d : distance) {
            if(d == max) answer++; 
        }
        
        return answer;
    }
    
    
    
    
    
}