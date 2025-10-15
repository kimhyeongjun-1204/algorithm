import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 원숭이의 인접 이동 (상, 하, 좌, 우)
    static int[] monkeyDx = {0, 0, 1, -1};
    static int[] monkeyDy = {1, -1, 0, 0};

    // 말의 이동 (나이트 이동 방식 8방향)
    static int[] horseDx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] horseDy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    // BFS 탐색을 위한 상태 클래스
    static class Node {
        int x, y, k, count;

        public Node(int x, int y, int k, int count) {
            this.x = x;
            this.y = y;
            this.k = k;         // 말처럼 점프한 횟수
            this.count = count; // 총 동작 수
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[K + 1][H][W];

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        // 시작점: (0, 0), 말 점프 횟수 0, 총 동작 수 0
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 목적지에 도착한 경우
            if (current.x == W - 1 && current.y == H - 1) {
                return current.count;
            }

            // 1. 원숭이처럼 인접한 4방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = current.x + monkeyDx[i];
                int ny = current.y + monkeyDy[i];

                // 격자 범위 내에 있고, 장애물이 아니며, 아직 방문하지 않은 경우
                if (nx >= 0 && ny >= 0 && nx < W && ny < H && map[ny][nx] == 0) {
                    if (!visited[current.k][ny][nx]) {
                        visited[current.k][ny][nx] = true;
                        queue.offer(new Node(nx, ny, current.k, current.count + 1));
                    }
                }
            }

            // 2. 말처럼 8방향으로 이동 (K번 미만으로 사용했을 경우에만)
            if (current.k < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = current.x + horseDx[i];
                    int ny = current.y + horseDy[i];

                    // 격자 범위 내에 있고, 장애물이 아니며, 아직 방문하지 않은 경우
                    if (nx >= 0 && ny >= 0 && nx < W && ny < H && map[ny][nx] == 0) {
                        // (k+1)번 점프를 사용해서 (ny, nx)에 방문하는 것이 처음일 때
                        if (!visited[current.k + 1][ny][nx]) {
                            visited[current.k + 1][ny][nx] = true;
                            queue.offer(new Node(nx, ny, current.k + 1, current.count + 1));
                        }
                    }
                }
            }
        }

        // 큐가 비었는데 목적지에 도달하지 못한 경우
        return -1;
    }
}