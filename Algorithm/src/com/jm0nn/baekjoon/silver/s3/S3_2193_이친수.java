package com.jm0nn.baekjoon.silver.s3;

import java.util.Scanner;

// 1로 시작하고, 1이 연속으로 나오지 않는 이진수의 개수를 구하는 문제
public class S3_2193_이친수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 이진수의 길이
		long[][] dp = new long[n][2]; // dp 배열

		// 가장 앞자리는 무조건 1
		dp[0][0] = 0;
		dp[0][1] = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					// 앞자리가 1이면서 뒷자리가 1이면 계산하지 않고 넘어감
					if (j == 1 && k == 1)
						continue;
					dp[i][k] += dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[n - 1][0] + dp[n - 1][1]);

		sc.close();
	}
}
