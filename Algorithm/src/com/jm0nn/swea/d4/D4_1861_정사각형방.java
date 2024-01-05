package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1861_정사각형방 {

	static final int[][] deltas = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	static int n;
	static Pos max;
	static int[][] arr;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static class Pos {
		int r;
		int c;
		int cnt;

		Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());
			arr = new int[n + 1][n + 1];

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}

			max = new Pos(0, 0, 0);
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					bfs(i, j);

			sb.append(arr[max.r][max.c]).append(' ').append(max.cnt).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	static void bfs(int x, int y) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(x, y, 1));

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			if (max.cnt < cnt) {
				max.r = x;
				max.c = y;
				max.cnt = cnt;
			} else if (max.cnt == cnt) {
				if (arr[max.r][max.c] > arr[x][y]) {
					max.r = x;
					max.c = y;
					max.cnt = cnt;
				}
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				if (1 > nr || nr > n || 1 > nc || nc > n)
					continue;

				if (arr[nr][nc] == arr[r][c] + 1)
					queue.offer(new Pos(nr, nc, cnt + 1));
			}
		}
	}

}
