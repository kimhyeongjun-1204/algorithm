class Solution {
    public String solution(String p) {
        String answer = "";
        
        if(p.equals("")) return ""; 
        
        
        return correctStr(p);
    }
    
    
    String correctStr(String p) {
        String answer = ""; 
        
        while(!p.equals("")) {
            String u , v; 
            
            int le = 0 , ri = 0; 
            int last = -1;
            for(int i=0;i<p.length();i++) {
                if(p.charAt(i) == '(') le++; 
                else ri++; 
                
                if(i != 0) {
                    if(ri == le) {
                        last = i; 
                        break; 
                    } 
                }
            }
            
            u = p.substring(0,last+1); 
            v = p.substring(last+1); 
            
            // System.out.println(" u : " + u + " v : " + v); 
            boolean correct = true; 
            le = 0; 
            for(int i=0;i<u.length();i++) {
                if(u.charAt(i) == '(') {
                    le++; 
                }else {
                    if(--le < 0) {
                        correct = false; 
                        break;  
                    }
                } 
            }
            if(correct) {
                answer += u; 
                p = v; 
                continue; 
            }else {
                String newStr = "("; 
                newStr += correctStr(v);
                newStr += ")"; 
                for(int i=1;i<u.length()-1;i++) {
                    if(u.charAt(i) == '(') newStr += ')';
                    else newStr += '('; 
                }
                return answer + newStr; 
            }
            
        }
        
        return answer; 
    }
    
    
    
}