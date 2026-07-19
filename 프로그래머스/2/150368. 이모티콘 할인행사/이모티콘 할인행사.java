import java.util.*;

class Solution {
    int eLen;
    int[] emoticons;
    int[][] users;
    int maxPlus = 0, maxBuy = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        eLen = emoticons.length;

        dfs(new int[eLen], 0);
        return new int[]{maxPlus, maxBuy};
    }

    void dfs(int[] rates, int depth) {
        if (depth == eLen) {
            calc(rates);
            return;
        }
        for (int r = 10; r <= 40; r += 10) {
            rates[depth] = r;
            dfs(rates, depth + 1);
        }
    }

    void calc(int[] rates) {
        int totalPlus = 0, totalBuy = 0;

        for (int[] user : users) {
            int sum = 0;
            boolean plus = false;

            for (int i = 0; i < eLen; i++) {
                if (user[0] <= rates[i]) {
                    sum += emoticons[i] * (100 - rates[i]) / 100;
                    if (sum >= user[1]) { plus = true; break; }
                }
            }

            if (plus) totalPlus++;
            else totalBuy += sum;
        }

        if (totalPlus > maxPlus || (totalPlus == maxPlus && totalBuy > maxBuy)) {
            maxPlus = totalPlus;
            maxBuy = totalBuy;
        }
    }
}