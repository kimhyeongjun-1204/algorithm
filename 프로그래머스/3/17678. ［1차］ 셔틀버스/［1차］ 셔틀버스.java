import java.util.*; 

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        // n : 운행 횟수 t : 운행 간격 m : 탑승 인원 timetable : 크루 도착 시각 
        Integer[] arrive = new Integer[timetable.length];  
        
        // 1. 출발 시간 정렬 
        int[] st = new int[n];
        for(int i=0;i<n;i++) {
            st[i] = 540 + t * i; 
        }
        
        // 2. 도착 시간 정렬 
        int i=0; 
        for(String time : timetable) {
            arrive[i++] = Integer.parseInt(time.split(":")[0])*60 + Integer.parseInt(time.split(":")[1]);
        }
        
        Arrays.sort(arrive , (a,b)-> {
            return a - b; 
        });
            
        int depart = 0; // 출발한 인원수 
        for(int j=0;j<n-1;j++) {
            if(depart >= arrive.length) break; 
            int stTime = st[j]; 
            int cnt = 0; 
            while(cnt < m && depart < arrive.length) {
                if(arrive[depart] > stTime) break; 
                cnt++; depart++;
            }
        }
        
        // 막차 고려 
        int last = st[n-1]; 
        int an = 0; 
        // System.out.println(arrive[depart] / 60 + ":" + arrive[depart] % 60);
        if(last < arrive[depart]) {
            an = last; 
        }else {
            int cnt = 0; 
            boolean full = false; 
            while(true) {
                if(cnt == m) {full = true; break;}
                // System.out.println(depart + " "  + arrive.length); 
                if(depart >= arrive.length || arrive[depart] > last) { an = last; break;}
                // System.out.println(arrive[depart] / 60 + ":" + arrive[depart] % 60);
                cnt++; depart++; 
            }
            
            // System.out.println(full); 
            if(full) an = arrive[depart-1]-1; 
        }
        
        String hour = (an / 60 < 10) ? "0"+an/60 : ""+an/60;
        String min = (an % 60 < 10) ? "0"+an%60 : ""+an%60; 
        
        
        return hour+":"+min;
    }
}