import java.util.*; 

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int len = computers.length; 
        boolean[] networks = new boolean[len]; 
        
        ArrayList<ArrayList<Integer>> connect = new ArrayList<>(); 
        for(int i=0;i<len;i++) {
            connect.add(new ArrayList<>()); 
        }
        for(int i=0;i<len;i++) {
            for(int j=0;j<len;j++) {
                if(i != j && computers[i][j] == 1) connect.get(i).add(j);  
            }
        }
        
        int networkNum = 0; 
        for(int i=0;i<len;i++) {
            if(networks[i]) continue; 
            bfs(i, connect, networks); 
            networkNum++; 
        }
        
        
        return networkNum;
    }
    
    void bfs(int computer , ArrayList<ArrayList<Integer>> connect , boolean[] networks) {
        Queue<Integer> queue = new ArrayDeque<>(); 
        queue.add(computer); 
        networks[computer] = true; 
        
        while(!queue.isEmpty()) {
            int now = queue.poll(); 
            for(int next : connect.get(now)) {
                if(!networks[next]) {
                    queue.add(next); 
                    networks[next] = true; 
                }
            }
        }
    }
}