import java.util.Arrays;
import java.lang.Math;
import java.lang.StringBuilder;

class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        
        boolean xLen = X.length() > Y.length() ? true : false; 
        int minLen = xLen ? Y.length() : X.length(); 
        
        int i;
        int[][] numArr = new int[2][10]; 
        for(i=0;i<minLen;i++) {
            numArr[0][X.charAt(i)-'0']++; 
            numArr[1][Y.charAt(i)-'0']++;
        }
        
        if(xLen) {
            for(;i<X.length();i++) {
                numArr[0][X.charAt(i)-'0']++;     
            }
        }else {
            for(;i<Y.length();i++) {
                numArr[1][Y.charAt(i)-'0']++;     
            }
        }
        
        // for(int[] num : numArr) {
        //     System.out.println(Arrays.toString(num));
        // }
        
        StringBuilder builder = new StringBuilder(); 
        
        for(int n=9;n>=0;n--) {
            if(numArr[0][n] > 0 && numArr[1][n] >0) {
                if(n == 0 && builder.toString().equals("")) break; 
                int repo = Math.min(numArr[0][n], numArr[1][n]); 
                for(int r=0;r<repo;r++) {
                    builder.append(n); 
                }
            }
        }
        
        if(builder.toString().equals("")) {
            if(numArr[0][0] > 0 && numArr[1][0] > 0) builder.append("0");
            else builder.append("-1"); 
        }
        
        answer = builder.toString(); 
        
        return answer;
    }
}