package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_11722_가장긴감소하는부분수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 수열의 크기
		int[] a = new int[n]; // 수열
		int[] dp = new int[n]; // dp 배열

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(st.nextToken());

		int answer = 0; // 답

		for (int i = 0; i < n; i++) {
			dp[i] = 1; // 시작은 1

			for (int j = 0; j < i; j++) {
				if (a[j] > a[i]) // 현재값이 이전값보다 작다면
					// 현재 길이와 이전의 최대길이 + 1을 비교하여 최댓값을 현재 dp에 갱신
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}

			answer = Math.max(answer, dp[i]);
		}

		System.out.println(answer);

		br.close();
	}

}
