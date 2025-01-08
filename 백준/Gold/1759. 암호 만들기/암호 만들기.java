import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백트래킹 문제 
public class Main {
    static char[] alpha; // 오름차순으로 정렬된 문자 배열
    static int alphaIndex = 0; // 주어진 알파벳 개수
    static int len; // 문자열 자리수
    static char[] moArr = {'a','e','i','o','u'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        len = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        alpha = new char[size];

        st = new StringTokenizer(br.readLine());
        // a t c i s w e
        while (st.hasMoreTokens()) {
            char ch = st.nextToken().charAt(0);
            int idx = alphaIndex;
            for (int i = 0; i < alphaIndex; i++) {
                if (ch < alpha[i]) {
                    idx = i;
                    break;
                }
            }

            char current = ch, tmp;
            for (int i = idx; i <= alphaIndex; i++) {
                tmp = alpha[i];
                alpha[i] = current;
                current = tmp;
            }
            alphaIndex++;
        }

        for(int i=0;i<alphaIndex;i++) {
            findNumber(0,i,"");
        }


    }

    // num = 몇개 담았는지 , 남은 개수 = alphaIndex - start
    static void findNumber(int num, int start, String str) {
        str += alpha[start];
        // 문자열을 다 만들었으면 종료함.
        if (++num == len) {
            int za_len = 0;
            int mo_len = 0;
            for(int i=0;i<len;i++) {
                char ch = str.charAt(i);
                boolean is_mo = false;
                for(char mo : moArr) {
                    if(ch == mo) {
                        is_mo = true;
                        break;
                    }
                }
                if(is_mo) {
                    mo_len++;
                }else {
                    za_len++;
                }

                if(za_len >= 2 && mo_len >= 1) {
                    System.out.println(str);
                    return;
                }
            }
            return;
        }

        int rest_arr = alphaIndex - (start+1); // 문자 배열에서 남은 자리수. 4개
        int rest_len = len - num; // 앞으로 담아야하는 자리수.   4개
        if (rest_len > rest_arr) {
            return;
        }

        for (int i = start + 1; i < alphaIndex; i++) {
            findNumber(num, i, str);
        }
    }
}
