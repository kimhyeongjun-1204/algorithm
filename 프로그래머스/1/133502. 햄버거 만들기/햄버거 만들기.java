import java.util.ArrayList; 

class Solution {
    ArrayList<Integer> list; 
    int[] hambuger = {2,3,1}; 
    int answer; 
    
    // 성공실패여부 
    public boolean collabo(int st) {
        for(int i=st;i<st+3;i++) {
            if(i >= list.size()  || list.get(i) != hambuger[i-st]) {
                return false; 
            }
        }
        
        
        for(int i=0;i<4;i++) System.out.println(list.remove(st-1));
        answer++; 
        return true; 
    }    
    
    public int solution(int[] ingredient) {
        answer = 0;
        
        list = new ArrayList<>(); 
        for (int i : ingredient) {
            list.add(i);
        }
        
        int index=0; 
        while(index < list.size()) {
            // System.out.println("size : " + list.size() + " index : " + index);
            if(list.get(index) == 1) {
                if(collabo(index+1)) {
                    index-=3;
                    if(index < 0) index = 0; 
                    continue; 
                }
            }
            index++;
        }
        
    
        
        
        
        return answer;
    }
}