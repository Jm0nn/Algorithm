package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014_활주로건설 {

	static int n, x, cnt;
	static int[] road;
	static int[][] field;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			road = new int[n];
			field = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					field[i][j] = Integer.parseInt(st.nextToken());
			}

			cnt = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					road[j] = field[i][j];
				getRoad();
			}

			for (int j = 0; j < n; j++) {
				for (int i = 0; i < n; i++)
					road[i] = field[i][j];
				getRoad();
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}

	static void getRoad() {
		int max = road[0];
		int min = road[0];

		for (int i = 1; i < n; i++) {
			if (Math.abs(road[i] - road[i - 1]) > 1)
				return;

			if (max < road[i])
				max = road[i];
			if (min > road[i])
				min = road[i];
		}

		if (max == min) {
			cnt++;
			return;
		}

		int h = min;

		while (h < max) {
			int start = 0;
			int end = 0;

			while (start < n) {
				if (road[start] != h) {
					start++;
					end++;
					continue;
				}

				for (int i = start + 1; i < n; i++) {
					if (road[i] == h)
						end = i;
					else
						break;
				}

				if (start - 1 >= 0 && road[start - 1] == h + 1) {
					if (end - start + 1 < x)
						return;
				}

				if (end + 1 < n && road[end + 1] == h + 1) {
					if (end - start + 1 < x)
						return;
				}

				if (start - 1 >= 0 && end + 1 < n && road[start - 1] == h + 1 && road[end + 1] == h + 1) {
					if (end - start + 1 < x * 2)
						return;
				}

				start = end++ + 1;
			}

			h++;
		}

		cnt++;
	}
}
