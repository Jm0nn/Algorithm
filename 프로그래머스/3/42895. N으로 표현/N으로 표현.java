import java.util.*;

public class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new Set[9];

        for (int i = N, digit = 1; digit <= 8; i = i * 10 + N, ++digit) {
            if (i == number) return digit;
            dp[digit] = new HashSet<>();
            dp[digit].add(i);
        }

        for (int i = 2; i <= 8; ++i) {
            for (int j = 1; j < i; ++j) {
                int k = i - j;
                for (int n1 : dp[j]) {
                    for (int n2 : dp[k]) {
                        dp[i].add(n1 + n2);
                        dp[i].add(n1 - n2);
                        dp[i].add(n1 * n2);
                        if (n2 != 0) dp[i].add(n1 / n2);
                    }
                }
            }

            if (dp[i].contains(number)) return i;
        }

        return -1;
    }
}
