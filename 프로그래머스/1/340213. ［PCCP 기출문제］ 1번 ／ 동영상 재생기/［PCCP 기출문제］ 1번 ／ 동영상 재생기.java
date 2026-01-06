class Solution {
    class Time { 
        int min, sec; 
        
        Time(int min, int sec) {
            this.min = min;
            this.sec = sec; 
        }
        
        boolean isLonger(Time t) {
            if(this.min > t.min) {
                return true; 
            }else if(this.min == t.min) {
                return this.sec >= t.sec;
            }else {
                return false; 
            }
        }
    }
    
    public Time command(Time v_len, Time current, Time op_s, Time op_e , String c) {
        int min = current.min; 
        int sec = current.sec; 
        
        if(c.equals("next")) {
            sec += 10; 
            if(sec >= 60) {
                sec %= 60; 
                min++; 
            }    
            current = new Time(min, sec);             
            if(current.isLonger(v_len)) {
                current = new Time(v_len.min, v_len.sec); 
            }
        }else {
            sec -= 10; 
            if(sec < 0) {
                sec += 60;
                min--; 
            }
            current = new Time(min, sec);             
            if(!current.isLonger(new Time(0,0))) {
                current = new Time(0, 0); 
            }        
        }
        
//         1. 오프닝 사이에 있을 경우  
        if(current.isLonger(op_s) && op_e.isLonger(current)) {
            current = new Time(op_e.min, op_e.sec); 
        }
        System.out.println("분 : " + current.min + "초 : " + current.sec);
        
        return current; 
    }
    
    public Time transfer(String str) {
        String[] st = str.split(":"); 
        
        return new Time(Integer.parseInt(st[0]) , Integer.parseInt(st[1])); 
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer; 
        
        Time v_len = transfer(video_len);
        Time po = transfer(pos);
        Time op_s = transfer(op_start);
        Time op_e = transfer(op_end);
        
        Time current = po; 
//         1. 오프닝 사이에 있을 경우  
        if(current.isLonger(op_s) && op_e.isLonger(current)) {
            current = new Time(op_e.min, op_e.sec); 
        }
        
        for(String c : commands) {
            current = command(v_len,current, op_s, op_e, c);   
        }
        
        String a_min = (current.min < 10) ? "0" + current.min : "" + current.min; 
        String a_sec = (current.sec < 10) ? "0" + current.sec : "" + current.sec; 
        
        answer = a_min + ":" + a_sec;
        
        return answer;
    }
}