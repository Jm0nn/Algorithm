package com.jm0nn.programmers.lv3.N으로표현;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {

//    private int N;
//    private Map<Integer, Integer> dp;
//
//    public int solution(int N, int number) {
//        this.N = N;
//        dp = new HashMap<>();
//
//        recur(0, 0);
//
//        return dp.getOrDefault(number, -1);
//    }
//
//    private void recur(int num, int cnt) {
//        if (cnt > 8 || (cnt > 0 && num == 0)) return;
//
//        dp.put(num, Math.min(dp.getOrDefault(num, cnt), cnt));
//
//        for (int i = N, digit = 1; i < 111_111_111; i = i * 10 + N, ++digit) {
//            int nCnt = cnt + digit;
//            recur(num + i, nCnt);
//            recur(num - i, nCnt);
//            recur(num * i, nCnt);
//            recur(num / i, nCnt);
//        }
//    }

//    public int solution(int N, int number) {
//        final int MAX_NUM = 32000;
//
//        int[] dp = new int[MAX_NUM + 1];
//
//        Arrays.fill(dp, 9);
//
//        for (int num = N, i = 1; num <= MAX_NUM; num = num * 10 + N, ++i) dp[num] = i;
//
//        while (true) {
//            boolean flag = false;
//
//            for (int i = 1; i <= MAX_NUM; ++i) {
//                if (dp[i] == 9) continue;
//
//                for (int j = 1; j <= MAX_NUM; ++j) {
//                    if (dp[j] == 9) continue;
//
//                    int cnt = dp[i] + dp[j];
//
//                    if (cnt > 8) continue;
//
//                    if (i + j <= MAX_NUM && dp[i + j] > cnt) {
//                        dp[i + j] = cnt;
//                        flag = true;
//                    }
//
//                    if (i - j > 0 && dp[i - j] > cnt) {
//                        dp[i - j] = cnt;
//                        flag = true;
//                    }
//
//                    if (i * j <= MAX_NUM && dp[i * j] > cnt) {
//                        dp[i * j] = cnt;
//                        flag = true;
//                    }
//
//                    if (i / j > 0 && dp[i / j] > cnt) {
//                        dp[i / j] = cnt;
//                        flag = true;
//                    }
//                }
//            }
//
//            if (!flag) break;
//        }
//
//        return dp[number] < 9 ? dp[number] : -1;
//    }

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
