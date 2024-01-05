package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S1_11057_오르막수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][10];

		for (int i = 0; i < 10; i++)
			dp[1][i] = 1;

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					dp[i][k] = (dp[i][k] + dp[i - 1][j]) % 10007;
				}
			}
		}

		int result = 0;
		for (int j = 0; j < 10; j++)
			result += dp[n][j];

		System.out.println(result % 10007);

		br.close();
	}
}
