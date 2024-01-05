package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_21736_헌내기는친구가필요해 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] campus = new char[n][m];
		boolean[][] visit = new boolean[n][m];
		int[] I = new int[2];

		for (int i = 0; i < n; ++i) {
			String input = br.readLine();
			for (int j = 0; j < m; ++j) {
				char c = input.charAt(j);

				if (c == 'X') {
					visit[i][j] = true;
				} else if (c == 'I') {
					I[0] = i;
					I[1] = j;
					visit[i][j] = true;
				}

				campus[i][j] = c;
			}
		}

		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(I);

		int cnt = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];

			if (campus[x][y] == 'P')
				++cnt;

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 > nx || nx >= n || 0 > ny || ny >= m || visit[nx][ny])
					continue;

				visit[nx][ny] = true;
				queue.offer(new int[] { nx, ny });
			}
		}

		System.out.println(cnt > 0 ? cnt : "TT");
	}

}
