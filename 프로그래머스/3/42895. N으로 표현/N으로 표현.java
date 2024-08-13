import java.util.*;

public class Solution {

    private int N;
    private Map<Integer, Integer> dp;

    public int solution(int N, int number) {
        this.N = N;
        dp = new HashMap<>();

        for (int i = N, digit = 1; i <= 1111111111; i = i * 10 + N, ++digit) dp.put(i, digit);

        recur(0, 0);

        return dp.getOrDefault(number, -1);
    }

    private void recur(int cnt, int num) {
        if (cnt > 8 || (cnt > 0 && num == 0)) return;

        dp.put(num, Math.min(dp.getOrDefault(num, cnt), cnt));

        for (int i = N, digit = 1; i < 1111111111; i = i * 10 + N, ++digit) {
            recur(cnt + digit, num + i);
            recur(cnt + digit, num - i);
            recur(cnt + digit, num * i);
            recur(cnt + digit, num / i);
        }
    }

}
