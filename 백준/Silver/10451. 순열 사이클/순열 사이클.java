import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        while(T-- > 0) {
            /* 1. 사용자 입력 */
            int N = Integer.parseInt(br.readLine());
            arr = new int[N+1];

            st = new StringTokenizer(br.readLine());
            int i=1;
            while(st.hasMoreTokens()) {
                arr[i++] = Integer.parseInt(st.nextToken());
            }

            /* 입력 좋료. */

            /* 2. 순열 알고리즘 */
            int count = 0;
            boolean[] visited = new boolean[N+1];

            for(int j=1; j<=N; j++) {
                if(visited[j]) continue;

                // 시작 노드 => j
                /* j로 돌아올때까지 사이클 실행 */
                int curr = j;
                do {
                    curr = arr[curr];
                    visited[curr] = true;
                } while (curr != j);
                count++;
            }
            System.out.println(count);
        }



    }

}
