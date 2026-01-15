import java.lang.Math;

class Solution {
    public String solution(String[] survey, int[] choices) {
//      survey : 판단 지표 | choices : 선택지    
        
        // 3 2 1 0 점 배분 
        String answer = "";
        
//      R  T   
//      C  F   
//      J  M 
//      A  N 
        int[] grade = new int[8]; 
        String character = "RTCFJMAN";
        
        int i=0; 
        for(String s : survey) {
            int c = choices[i]; 
            char ch; 
            int plus = Math.abs(c - 4); 
            if(c < 4) {
                ch = s.charAt(0); 
            }else {
                ch = s.charAt(1); 
            }
            
            int index = character.indexOf(ch); 
            grade[index] += plus; 
            
            i++; 
        }
        
        
        for(i=0;i<8;i+=2) {
            if(grade[i] >= grade[i+1]) {
                answer += character.charAt(i); 
            }else {
                answer += character.charAt(i+1); 
            }
        }
        
        return answer;
    }
}