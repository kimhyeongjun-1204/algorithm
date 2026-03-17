import java.util.*; 

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        
        String[] title = new String[musicinfos.length]; 
        String[] sing = new String[musicinfos.length]; 
        
        int idx = 0; 
        for(String musicI : musicinfos) {
            String[] infos = musicI.split(","); 
            
            int st = parseMin(infos[0]); 
            int end = parseMin(infos[1]); 
            title[idx] = infos[2]; 
            
            int length = end - st; 
            StringBuilder playMusic = new StringBuilder(); 
            int cnt = 0, i = 0;
            String music = infos[3]; 
            
            while(cnt < length) {
                char ch = music.charAt(i); 
                playMusic.append(ch); 
                if(ch != '#') cnt++; 
                i = (i+1) % music.length(); 
            }
            // D#C 
            if(i != music.length() && music.charAt(i) == '#') 
                playMusic.append('#');
            
            sing[idx] = playMusic.toString(); 
            
            idx++; 
        }
        
        int sIdx = 0; 
        int maxIdx = -1; 
        int maxPlay = 0; 
        for(String s : sing) {
        int searchFrom = 0;
        while((searchFrom = s.indexOf(m, searchFrom)) != -1) {
            int nextIdx = searchFrom + m.length();
            if(nextIdx >= s.length() || s.charAt(nextIdx) != '#') {
                int playtime = s.length();
                if(maxPlay < playtime) {
                    maxPlay = playtime;
                    maxIdx = sIdx;
                }
                break;
            }
            searchFrom++;
        }
        sIdx++;
    }
        
        if(maxIdx == -1) return "(None)";
        
        return title[maxIdx];
    }
    
    int parseMin(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        
        return hour*60 + min; 
    }
}