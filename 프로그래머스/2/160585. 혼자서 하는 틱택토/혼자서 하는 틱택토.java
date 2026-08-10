class Solution {
    public int solution(String[] board) {
        int n = 3; 
        char[][] map = new char[n][n]; 
        int[][] move = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}}; 
        boolean winO = false, winX = false; 
        
        int i=0; 
        for(String st : board) {
            map[i++] = st.toCharArray(); 
        }
        
        int oNum = 0, xNum = 0; 
        for(i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                char ch = map[i][j]; 
                
                if(ch != '.') {
                    for(int[] m : move) {
                        int nR = i, nC = j; 
                        int k; 
                        
                        for(k=0;k<2;k++) {  
                            nR += m[0]; nC += m[1]; 
                            if(nR < 0 || nR >= n || nC < 0 || nC >= n) break;
                            if(map[nR][nC] != ch) break;
                        }
                        
                        if(k == 2) {
                            if(ch == 'O') winO = true;
                            else winX = true; 
                        }
                    }
                    
                    if(ch == 'O') {
                        oNum++; 
                    }else {
                        xNum++; 
                    }
                }
            }
        }
        
        System.out.println(winO + "," + winX); 
        if(winO && winX) return 0; 
        if(winO && (oNum-1) == xNum) return 1; 
        if(winX && oNum == xNum) return 1;
        if(!winO && !winX && (oNum == xNum || oNum == (xNum+1))) return 1; 
        
        
        // 선공 승리, 후공 승리 둘다 승리 x
        
        return 0;
    }
}