package com.jm0nn.baekjoon.silver.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S3_9095_123더하기 {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		int[] dp = new int[11];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for (int i = 4; i <= 10; i++)
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		
		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append(dp[n]).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
}
