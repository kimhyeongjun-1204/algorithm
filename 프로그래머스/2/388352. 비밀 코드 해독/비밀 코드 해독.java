import java.util.*; 


class Solution {
    List<ArrayList<Integer>> code = new ArrayList<>(); 
    int n; 
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0; 
        this.n = n; 
        
        dfs(1, new ArrayList<>(),0); 
        
        int idx = 0; 
        for(int[] ques : q) {
            int an = ans[idx]; 
            
            for(int i=0;i<code.size();i++) {
                ArrayList<Integer> list = code.get(i); 
                int liIdx = 0, qIdx = 0; 
                int cnt = 0; 
                
                while(liIdx < 5 && qIdx < 5) {
                    if(list.get(liIdx) > ques[qIdx]) {
                        qIdx++; 
                    }else if(list.get(liIdx) < ques[qIdx]) {
                        liIdx++; 
                    }else {
                        cnt++; 
                        liIdx++; qIdx++; 
                    }
                }
                
                if(cnt != an) {code.remove(i); i--; }
            }
            
            idx++; 
        }
        
        
        return code.size();
    }
    
    void dfs(int num, ArrayList<Integer> list,int size) {
        if(size >= 5) {code.add(new ArrayList<>(list)); return;}
        if(num > n) return; 
        
        list.add(num); 
        dfs(num+1, list,size+1); 
        list.remove(list.size()-1); 
        dfs(num+1, list, size); 
    }
}