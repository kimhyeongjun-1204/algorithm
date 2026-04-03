import java.util.*; 

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        boolean[][] filled = new boolean[102][102];
        
        // 1) 좌표 2배 확대 후 사각형 내부를 모두 채움
        for (int[] rect : rectangle) {
            for (int x = rect[0]*2; x <= rect[2]*2; x++)
                for (int y = rect[1]*2; y <= rect[3]*2; y++)
                    filled[x][y] = true;
        }
        
        // 2) 내부(테두리가 아닌 부분)를 제거 → 테두리만 남김
        for (int[] rect : rectangle) {
            for (int x = rect[0]*2+1; x < rect[2]*2; x++)
                for (int y = rect[1]*2+1; y < rect[3]*2; y++)
                    filled[x][y] = false;
        }
        
        // 3) BFS로 테두리 위의 최단 거리 탐색
        int sx = characterX*2, sy = characterY*2;
        int ex = itemX*2,      ey = itemY*2;
        int[][] dist = new int[102][102];
        for (int[] row : dist) Arrays.fill(row, -1);
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        dist[sx][sy] = 0;
        
        int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0]+dx[d], ny = cur[1]+dy[d];
                if (nx<0||ny<0||nx>101||ny>101) continue;
                if (!filled[nx][ny] || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }
        
        return dist[ex][ey] / 2;  // 2배 확대했으므로 나누기 2
    }
}