package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5650_핀볼게임 {

	static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int n, point;
	static int[][] map;
	static Pos[][] wh;
	static boolean[][][] visit;

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine().trim());

			map = new int[n][n];
			wh = new Pos[11][2];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 5) {
						if (wh[map[i][j]][0] == null)
							wh[map[i][j]][0] = new Pos(i, j);
						else
							wh[map[i][j]][1] = new Pos(i, j);
					}
				}
			}

			point = 0;

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (map[i][j] == 0)
						for (int d = 0; d < 4; d++)
							game(i, j, d);

			sb.append(point).append('\n');
		}

		System.out.println(sb);
	}

	static void game(int sr, int sc, int dir) {
		int p = 0;

		int r = sr, c = sc, d = dir;
		boolean gameOver = false;
		while (!gameOver) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			if (0 > nr || nr >= n || 0 > nc || nc >= n) {
				p++;
				d = (d + 2) % 4;
			} else {
				switch (map[nr][nc]) {
				case 1:
					p++;
					switch (d) {
					case 0:
					case 1:
						d = (d + 2) % 4;
						break;

					case 2:
						d = 1;
						break;

					case 3:
						d = 0;
						break;
					}
					break;

				case 2:
					p++;
					switch (d) {
					case 1:
					case 2:
						d = (d + 2) % 4;
						break;

					case 0:
						d = 1;
						break;

					case 3:
						d = 2;
						break;
					}
					break;

				case 3:
					p++;
					switch (d) {
					case 2:
					case 3:
						d = (d + 2) % 4;
						break;

					case 0:
						d = 3;
						break;

					case 1:
						d = 2;
						break;
					}
					break;

				case 4:
					p++;
					switch (d) {
					case 0:
					case 3:
						d = (d + 2) % 4;
						break;

					case 1:
						d = 0;
						break;

					case 2:
						d = 3;
						break;
					}
					break;

				case 5:
					p++;
					d = (d + 2) % 4;
					break;

				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
					Pos p1 = wh[map[nr][nc]][0];
					Pos p2 = wh[map[nr][nc]][1];

					if (nr == p1.r && nc == p1.c) {
						nr = p2.r;
						nc = p2.c;
					} else {
						nr = p1.r;
						nc = p1.c;
					}
					break;

				case -1:
					gameOver = true;
					break;

				}
			}

			r = nr;
			c = nc;

			if (r == sr && c == sc) {
				gameOver = true;
			}
		}

		if (point < p)
			point = p;
	}

}
