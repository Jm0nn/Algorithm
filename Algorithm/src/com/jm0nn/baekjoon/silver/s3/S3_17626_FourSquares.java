package com.jm0nn.baekjoon.silver.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S3_17626_FourSquares {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; ++i) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j * j <= i; ++j)
				min = Math.min(min, dp[i - j * j]);
			dp[i] = min + 1;
		}
		System.out.println(dp[n]);
	}
}
