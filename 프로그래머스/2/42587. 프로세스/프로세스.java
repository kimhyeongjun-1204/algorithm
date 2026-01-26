import java.util.*; 

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1; 
        
        StringBuilder sb = new StringBuilder(); 
        
        int num = priorities[location]; 
        int length = priorities.length; 
        
        int index=0; 
        for(int p : priorities) {
            if(index == location) {
                sb.append('*'); 
                index++;
                continue; 
            }
            sb.append((char)(p+'0')); 
            index++; 
        } 
        
        for(int i=9;i>num;i--) {
            StringBuilder move = new StringBuilder(); 
            String del = Integer.toString(i); 
            
            while(sb.indexOf(del) != -1) {
                int removeIdx = sb.indexOf(del); 
                String result = sb.substring(removeIdx+1) + sb.substring(0, removeIdx);
                sb = new StringBuilder(result);
                // System.out.println(sb); 
                answer++;  
            }
        }    
        
        for(int i=0;i<sb.indexOf("*");i++) {
            if(sb.charAt(i) == (char)(num + '0')) answer++; 
        } 
        
        
        return answer;
    }
}