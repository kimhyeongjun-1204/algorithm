class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        // 1) 차분 배열 (한 칸씩 크게)
        int[][] diff = new int[n + 1][m + 1];
        
        for (int[] s : skill) {
            int type = s[0], r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4], degree = s[5];
            
            int d = (type == 1) ? -degree : degree;
            
            diff[r1][c1]       += d;
            diff[r1][c2 + 1]   -= d;
            diff[r2 + 1][c1]   -= d;
            diff[r2 + 1][c2 + 1] += d;
        }
        
        // 2) 행 방향 누적합
        for (int i = 0; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }
        
        // 3) 열 방향 누적합
        for (int j = 0; j < m + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }
        
        // 4) 원본 board에 반영 후 카운트
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + diff[i][j] > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}