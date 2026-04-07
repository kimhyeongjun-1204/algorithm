import java.util.*; 

class Solution {
    ArrayList<Integer> B = new ArrayList<>(); 
    int answer; 
    
    public int solution(int[] A, int[] B) {

        Arrays.sort(A); 
        Arrays.sort(B); 
        for(int num : B) 
            this.B.add(num); 
        
        for(int numA : A) {
            if(!win(numA)) {
                break; 
            }
        }
        
        return answer;
    }
    
    boolean win(int numA) { 
        for(int numB : B) {
            if(numA < numB) {
                B.remove((Object)numB);
                answer++; 
                return true; 
            }
        }        
        
        return false; 
    }
}