import java.util.*; 

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length]; 
        int n = 0; 
        ArrayList<Integer> y = new ArrayList<>(); 
        y.add(k); 
                
        while(k != 1) {
            if(k % 2 == 0) {
                k /= 2; 
            }else {
                k = k*3 + 1;                 
            }
            y.add(k); 
            n++; 
        }

        double[] area = new double[n]; 
        int i = 0; 
        for(int x=0;x<n;x++) {
            area[i++] = (y.get(x) + y.get(x+1)) / 2.0;             
        }
        
        i=0; 
        for(int[] range : ranges) {
            int st = range[0], end = n+range[1]; 
            double sum = 0; 
            
            for(int x=st;x<end;x++) {
                sum += area[x]; 
            }
            
            if(end < st) {answer[i++] = -1; continue;}
            answer[i++] = sum; 
        }
        
        
        
        return answer;
    }
}