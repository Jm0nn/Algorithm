package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 극장에서 사람이 좌석에 앉는 경우의 수를 구하는 문제
public class S1_2302_극장좌석 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 좌석의 수
		int m = Integer.parseInt(br.readLine()); // vip석의 수
		int[] dp = new int[n + 1]; // dp 배열
		int[] vip = new int[m + 1]; // vip석 배열

		for (int i = 1; i <= m; i++)
			vip[i] = Integer.parseInt(br.readLine());

		// vip석은 무조건 고정 자리
		// 일반석은 좌우로 한칸씩 바꿔서 앉을 수 있음
		// N개의 좌석이 있을 때 좌우로 한칸씩 바꿔 앉을 수 있다면
		// 경우의 수는 피보나치수와 같음
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 2];

		int result = 1; // 답

		// vip석을 기준으로 구간을 나눠서 결과를 곱해서 답을 구함
		for (int i = 1; i <= m; i++)
			result *= dp[vip[i] - vip[i - 1] - 1];
		result *= dp[n - vip[m]];

		System.out.println(result);

		br.close();
	}
}
