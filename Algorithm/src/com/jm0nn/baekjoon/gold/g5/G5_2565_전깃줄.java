package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_2565_전깃줄 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] wire = new int[n][2];
        int[] dp = new int[n];

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(wire, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (wire[i][1] > wire[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, dp[i]);

        System.out.println(n - max);
    }

}
