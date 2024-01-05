package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S1_1389_케빈베이컨의6단계법칙 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int INF = 99999;

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] rel = new int[n + 1][n + 1];
		for (int i = 0; i <= n; ++i)
			Arrays.fill(rel[i], INF);

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			rel[a][b] = 1;
			rel[b][a] = 1;
		}

		for (int k = 1; k <= n; ++k) {
			for (int i = 1; i <= n; ++i) {
				if (i == k)
					continue;

				for (int j = 0; j <= n; ++j) {
					if (k == j || i == j)
						continue;

					if (rel[i][j] > rel[i][k] + rel[k][j])
						rel[i][j] = rel[i][k] + rel[k][j];
				}
			}
		}

		int[] kb = new int[n + 1];
		int min = Integer.MAX_VALUE;
		int num = 0;

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (rel[i][j] == INF)
					continue;

				kb[i] += rel[i][j];
			}

			if (min > kb[i]) {
				min = kb[i];
				num = i;
			}
		}

		System.out.println(num);
	}

}
