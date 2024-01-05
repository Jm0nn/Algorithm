package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_11404_플로이드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int INF = 99999999;
		int[][] dist = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i)
			Arrays.fill(dist[i], INF);

		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = dist[a][b] < c ? dist[a][b] : c;
		}

		for (int k = 1; k <= n; ++k) {
			for (int i = 1; i <= n; ++i) {
				if (i == k)
					continue;

				for (int j = 1; j <= n; ++j) {
					if (k == j || i == j)
						continue;

					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j)
				sb.append(dist[i][j] < INF ? dist[i][j] : 0).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
