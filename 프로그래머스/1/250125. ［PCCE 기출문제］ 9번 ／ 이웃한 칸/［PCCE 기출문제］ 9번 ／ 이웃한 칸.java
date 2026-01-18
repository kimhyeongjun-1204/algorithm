class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        int[][] move = {
            {-1,0}, 
            {1,0}, 
            {0,-1}, 
            {0,1}
        };
        
        String color = board[h][w]; 
        for(int i=0;i<4;i++) {
            int r = h + move[i][0],
                c = w + move[i][1]; 
            
            if(r >= 0 && c >=0 && r < board.length && c < board[0].length) {
                if(board[r][c].equals(color)) {
                    answer++; 
                }
            }
            
        }
        
        
        
        return answer;
    }
}