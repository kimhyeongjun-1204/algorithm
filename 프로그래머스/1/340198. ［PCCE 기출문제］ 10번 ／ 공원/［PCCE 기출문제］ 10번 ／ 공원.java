class Solution {
    public boolean availableMats(int mat , String[][] park) {
            System.out.println("mat : " + mat); 
            
            for(int i=0;i<park.length;i++) {
                for(int j = 0;j<park[0].length;j++) {
                    if(park[i][j].equals("-1")) {
                        System.out.println("i : " + i + " j : " + j); 
                        if(check(mat ,park, i , j)) {
                            return true; 
                        }
                    }
                }
            }
        
        return false; 
    }
    
    public boolean check(int mat ,String[][] park, int r, int c) {
        if(r+mat > park.length || c+mat > park[0].length) return false; 
        for(int i=r;i<r+mat;i++) {
            for(int j=c;j<c+mat;j++) {
                if(!park[i][j].equals("-1")) return false; 
            }
        }
        
        return true; 
    }
    
    public int solution(int[] mats, String[][] park) {
        // mats => 돗자리 크기 , park => 배치도 
        for(int i=0;i<mats.length;i++) {
            for(int j=i;j<mats.length;j++) {
                if(mats[i] < mats[j]) {
                    int p = mats[i]; 
                    mats[i] = mats[j]; 
                    mats[j] = p;                               
                }
            }
            if(availableMats(mats[i], park)) {
                return mats[i]; 
            } 
        }
        
        return -1; 
    }
}