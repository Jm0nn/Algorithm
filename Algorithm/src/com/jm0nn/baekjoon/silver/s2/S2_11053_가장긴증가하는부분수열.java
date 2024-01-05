package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_11053_가장긴증가하는부분수열 {
	private static int[] arr;
	private static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
//			dp[i] = 1;
//			
//			// 이전 원소 탐색
//			for (int j = 0; j < i; j++) {
//			
//				// j번째 원소가 i번째 원소보다 작으면서
//				// i번째 dp가 j번째 dp+1보다 작은 경우
//				if (arr[j] < arr[i] && dp[i] < dp[j] + 1)
//					dp[i] = dp[j] + 1;
//			}
			
			LIS(i);
		}
		
		int max = dp[0];
		for (int i = 1; i < n; i++)
			max = Math.max(dp[i], max);
		
		System.out.println(max);
		
		br.close();
	}
	
	private static int LIS(int n) {
		// 탐색하지 않았을 때
		if (dp[n] == 0) {
			dp[n] = 1;	// 1로 초기화
			
			// n 이전의 노드 탐색
			for (int i = n - 1; i >= 0; i--) {
				// 이전의 노드 중 arr[n]보다 작은 걸 발견했을 경우
				if (arr[i] < arr[n])
					dp[n] = Math.max(dp[n], LIS(i) + 1);
			}
		}
		
		return dp[n];
	}
}
