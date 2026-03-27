import java.util.*;

class Solution {
    int n;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        
        // 빈칸 추출 (game_board에서 0인 영역)
        List<List<int[]>> blanks = extract(game_board, 0);
        // 조각 추출 (table에서 1인 영역)
        List<List<int[]>> pieces = extract(table, 1);
        
        boolean[] usedPiece = new boolean[pieces.size()];
        int answer = 0;
        
        for (List<int[]> blank : blanks) {
            for (int i = 0; i < pieces.size(); i++) {
                if (usedPiece[i]) continue;
                if (blank.size() != pieces.get(i).size()) continue;
                if (matches(blank, pieces.get(i))) {
                    usedPiece[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }
        
        return answer;
    }
    
    // target 값으로 연결된 영역들을 BFS로 추출
    List<List<int[]>> extract(int[][] board, int target) {
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == target && !visited[i][j]) {
                    List<int[]> block = new ArrayList<>();
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        block.add(cur);
                        for (int d = 0; d < 4; d++) {
                            int nx = cur[0] + dx[d];
                            int ny = cur[1] + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n
                                && !visited[nx][ny] && board[nx][ny] == target) {
                                visited[nx][ny] = true;
                                queue.add(new int[]{nx, ny});
                            }
                        }
                    }
                    result.add(normalize(block));
                }
            }
        }
        return result;
    }
    
    // 좌표를 (0,0) 기준으로 정규화
    List<int[]> normalize(List<int[]> block) {
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
        for (int[] p : block) {
            minR = Math.min(minR, p[0]);
            minC = Math.min(minC, p[1]);
        }
        List<int[]> normalized = new ArrayList<>();
        for (int[] p : block) {
            normalized.add(new int[]{p[0] - minR, p[1] - minC});
        }
        normalized.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        return normalized;
    }
    
    // 90도 회전
    List<int[]> rotate(List<int[]> block) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] p : block) {
            rotated.add(new int[]{p[1], -p[0]});
        }
        return normalize(rotated);
    }
    
    // 4방향 회전 중 하나라도 일치하면 true
    boolean matches(List<int[]> blank, List<int[]> piece) {
        List<int[]> rotated = piece;
        for (int r = 0; r < 4; r++) {
            if (isSame(blank, rotated)) return true;
            rotated = rotate(rotated);
        }
        return false;
    }
    
    boolean isSame(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1])
                return false;
        }
        return true;
    }
}