import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenzier = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        int N = Integer.parseInt(tokenzier.nextToken());
        int C = Integer.parseInt(tokenzier.nextToken());

        // 1. 공유기 위치 입력받기
        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(br.readLine());
            list.add(num);
        }

        Collections.sort(list);

        // 2. 공유기 사이 최대 거리 구하기
        int left = 1;
        int right = list.get(N-1) - list.get(0);
        int mid = 0;
        int answer = 0;

        while(left <= right) {
            mid = (left + right) / 2;
            if(checkAvailable(list,C,mid)) {
                left = mid + 1;
                answer = mid; 
            }else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    // 최대거리 가능한지 여부 체크
    static boolean checkAvailable(ArrayList<Integer> list, int C,int mid) {
        int lastPosition = list.get(0);
        int count = 1;

        for(int i = 1; i < list.size(); i++) {
            int num = list.get(i);
            int distance = num - lastPosition;
            if(distance >= mid) {
                count++;
                lastPosition = list.get(i);
            }
            if(count >= C) return true;
        }

        return false;
    }
}
