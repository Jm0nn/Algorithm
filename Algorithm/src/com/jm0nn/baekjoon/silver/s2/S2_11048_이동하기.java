package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_11048_이동하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dp[n][m]);

		br.close();
	}
}
