package com.jm0nn.swea.d6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D6_1263_사람네트워크2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int INF = 999999;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[][] dp = new int[n][n];

			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					dp[i][j] = Integer.parseInt(st.nextToken());

					if (i != j && dp[i][j] == 0)
						dp[i][j] = INF;
				}
			}

			for (int k = 0; k < n; ++k) {
				for (int i = 0; i < n; ++i) {
					if (k == i)
						continue;

					for (int j = 0; j < n; ++j) {
						if (k == j || i == j)
							continue;

						if (dp[i][j] > dp[i][k] + dp[k][j])
							dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}

			int min = Integer.MAX_VALUE;

			for (int i = 0; i < n; ++i) {
				int sum = 0;

				for (int j = 0; j < n; ++j)
					sum += dp[i][j];

				if (min > sum)
					min = sum;
			}

			sb.append(min).append('\n');
		}

		System.out.println(sb);
	}

}
