package com.jm0nn.baekjoon.silver.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S3_2579_계단오르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] step = new int[n + 1];
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++)
			step[i] = Integer.parseInt(br.readLine());

		dp[1] = step[1];

		if (n > 1)
			dp[2] = step[1] + step[2];

		for (int i = 3; i <= n; i++)
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + step[i - 1]) + step[i];

		System.out.println(dp[n]);

		br.close();
	}

}
