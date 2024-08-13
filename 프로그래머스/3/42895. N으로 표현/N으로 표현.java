import java.util.*;

public class Solution {
    public int solution(int N, int number) {
        int answer = -1;

        final int MAX_NUM = 32000;

        int[] dp = new int[MAX_NUM + 1];

        Arrays.fill(dp, 9);

        int tmp = N;
        for (int j = 1; j <= 8; ++j) {
            if (tmp > MAX_NUM) break;

            dp[tmp] = j;
            tmp = tmp * 10 + N;
        }

        while (true) {
            boolean flag = false;

            for (int i = 1; i <= MAX_NUM; ++i) {
                if (dp[i] == 9) continue;

                for (int j = 1; j <= MAX_NUM; ++j) {
                    if (dp[j] == 9) continue;

                    int cnt = dp[i] + dp[j];

                    if (cnt > 8) continue;

                    if (i + j <= MAX_NUM && dp[i + j] > cnt) {
                        dp[i + j] = cnt;
                        flag = true;
                    }

                    if (i - j > 0 && dp[i - j] > cnt) {
                        dp[i - j] = cnt;
                        flag = true;
                    }

                    if (i * j <= MAX_NUM && dp[i * j] > cnt) {
                        dp[i * j] = cnt;
                        flag = true;
                    }

                    if (i / j > 0 && dp[i / j] > cnt) {
                        dp[i / j] = cnt;
                        flag = true;
                    }
                }
            }

            if (dp[number] < 9 || !flag) break;
        }

        return dp[number] < 9 ? dp[number] : -1;
    }
}
