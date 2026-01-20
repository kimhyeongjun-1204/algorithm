import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        Set<long[]> points = new HashSet<>();
        
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for (int i = 0; i < line.length; i++) {
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];

            for (int j = i + 1; j < line.length; j++) {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];

                long denominator = a * d - b * c;
                
                // 1. 평행하거나 일치하는 경우
                if (denominator == 0) continue;

                long xNumerator = b * f - e * d;
                long yNumerator = e * c - a * f;

                // 2. 정수 좌표인지 확인
                if (xNumerator % denominator == 0 && yNumerator % denominator == 0) {
                    long x = xNumerator / denominator;
                    long y = yNumerator / denominator;

                    points.add(new long[]{x, y});

                    // 3. 최소/최대 범위 갱신
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        // 4. 격자판 크기 결정 및 초기화
        int height = (int) (maxY - minY + 1);
        int width = (int) (maxX - minX + 1);
        
        char[][] board = new char[height][width];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // 5. 별 찍기 (좌표 변환)
        for (long[] p : points) {
            int x = (int) (p[0] - minX);
            int y = (int) (maxY - p[1]); // y축은 위아래가 반대
            board[y][x] = '*';
        }

        // 6. 결과 문자열 배열 생성
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(board[i]);
        }

        return answer;
    }
}