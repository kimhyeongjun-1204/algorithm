import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] before_row;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int before = 0;
        before_row = new int[M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                int num = Integer.parseInt(st.nextToken());

                int max = Math.max(before_row[j], before);
                before_row[j] = max + num;
                before = before_row[j];
            }
            before = 0;
        }

        System.out.println(before_row[M-1]);

    }

}
