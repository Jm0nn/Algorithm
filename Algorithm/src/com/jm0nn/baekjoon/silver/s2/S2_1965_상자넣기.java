package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최장 증가 부분 수열
public class S2_1965_상자넣기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] box = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			box[i] = Integer.parseInt(st.nextToken());

		int[] dp = new int[n];

		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (box[i] > box[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}

		int max = 0;
		for (int i = 0; i < n; i++)
			max = Math.max(max, dp[i]);

		System.out.println(max);

		br.close();
	}
}
