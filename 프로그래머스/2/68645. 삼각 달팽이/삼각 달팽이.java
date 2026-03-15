import java.util.*; 

class Solution {
    public ArrayList solution(int n) {
        ArrayList<Integer> answer = new ArrayList<>(); 
        
        int sum = 0; 
        for(int i=1;i<=n;i++) {
            sum += i; 
        }
        
        int[][] tri = new int[n][]; 
        
        for(int i=0;i<n;i++) {
            tri[i] = new int[i+1]; 
        }
        tri[0][0] = 1; 
        
        // 방향 1 : (+1, 0) 방향 2 : (0 , +1) 방향 3 : (-1 , -1)        
        Point now = new Point(0,0);
        int num = 2; // 채울 값 
        Point[] direct = new Point[]{new Point(1,0) ,new Point(0,1) , new Point(-1,-1)};
        int diNow = 1; // 0,1,2 
                
        while(true) {
            while(true) {
                now.plus(direct[diNow]); 
                if(now.r < 0 || now.r >= n || now.c < 0 || now.c > now.r || tri[now.r][now.c] != 0) {
                    now.minus(direct[diNow]); 
                    diNow = (diNow + 1) % 3;
                    break; 
                }
                tri[now.r][now.c] = num++; 
                if(sum < num) break; 
            }
            if(sum < num) break; 
        }
        
        for(int[] t : tri) {
            for(int i=0;i<t.length;i++) {
                answer.add(t[i]); 
            }
        }
        
        
        return answer;
    }
    
    class Point {
        int r,c; 
        
        Point(int r, int c) {
            this.r = r; 
            this.c = c; 
        }
        
        void plus(Point p) {
            this.r += p.r; 
            this.c += p.c; 
        }
        
        void minus(Point p) {
            this.r -= p.r; 
            this.c -= p.c; 
        }
    }
    
}