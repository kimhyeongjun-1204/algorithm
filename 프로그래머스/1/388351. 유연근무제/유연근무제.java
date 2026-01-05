class Solution {
    public void changeClock(int[] schedules) {
        for(int i=0;i<schedules.length;i++) { 
            int num = schedules[i]; 
            
            int hun = num / 100; 
            int ten = num % 100 / 10; 
            int  n  = num % 10; 
            
            if(ten + 1 >= 6) {
                hun++; 
                ten = (ten+1) % 6; 
            }else {
                ten++; 
            }
            
            schedules[i] = hun*100 + ten*10 + n; 
        }
        
    }
    
    public int goWork(int schedules ,int[] timelogs, int st) {
        int len = timelogs.length; 
        int i = 0; 
        
        while(i < len) {
            if(st == 6 || st == 7 ) {
                i++;
                st = (st%7) + 1;
                continue; 
            }
            
            if(schedules < timelogs[i]) {
                return 0; 
            }
            i++;
            st = (st%7) + 1;
        }
        
        return 1; 
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        changeClock(schedules); 
        for(int i=0;i<schedules.length;i++) {
            answer += goWork(schedules[i], timelogs[i], startday);         
        }
        
        return answer;
    }
}