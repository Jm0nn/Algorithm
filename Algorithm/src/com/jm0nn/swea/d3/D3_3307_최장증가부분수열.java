package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3307_최장증가부분수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			int n = Integer.parseInt(br.readLine());

			int[] arr = new int[n];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; ++i)
				arr[i] = Integer.parseInt(st.nextToken());

			int[] dp = new int[n];
			int max = 0;

			for (int i = 0; i < n; ++i) {
				dp[i] = 1;

				for (int j = 0; j < i; ++j) {
					if (arr[j] < arr[i] && dp[i] < dp[j] + 1)
						dp[i] = dp[j] + 1;
				}

				if (max < dp[i])
					max = dp[i];
			}

			sb.append(max).append('\n');
		}

		System.out.println(sb);
	}

}
